package com.santimattius.kmp.skeleton.core.data.source

import android.content.Intent
import android.net.Uri
import com.google.android.play.core.review.ReviewException
import com.google.android.play.core.review.ReviewManagerFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class GooglePlayReviewDataSource(
    private val params: ReviewSourceParams,
) : ReviewDataSource {
    override fun requestReview(): Flow<ReviewResultCode> {
        return flow {
            val activity = params.activity
            val manager = ReviewManagerFactory.create(activity)
            val reviewInfo = manager.requestReviewFlow().await()
            manager.launchReviewFlow(activity, reviewInfo).await()
            emit(ReviewResultCode.NO_ERROR)
        }.catch { e ->
            if (e is ReviewException) {
                emit(ReviewResultCode.fromCode(e.errorCode))
            } else {
                throw e
            }
        }
    }

    override fun requestInStoreReview(): Flow<ReviewResultCode> = flow {
        val activity = params.activity
        val packageName = activity.packageName

        val storeUri = Uri.parse("market://details?id=$packageName")
        val fallbackStoreUri =
            Uri.parse("http://play.google.com/store/apps/details?id=$packageName")

        val marketAppIntent = Intent(Intent.ACTION_VIEW, storeUri).apply {
            flags += Intent.FLAG_ACTIVITY_NO_HISTORY or
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        }

        val marketInBrowserIntent = Intent(Intent.ACTION_VIEW, fallbackStoreUri)

        runCatching {
            activity.startActivity(marketAppIntent)
        }.getOrElse {
            activity.startActivity(marketInBrowserIntent)
        }
        emit(ReviewResultCode.NO_ERROR)
    }
}