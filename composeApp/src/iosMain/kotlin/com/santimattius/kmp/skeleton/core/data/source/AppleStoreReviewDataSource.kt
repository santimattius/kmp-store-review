package com.santimattius.kmp.skeleton.core.data.source

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import platform.Foundation.NSURL
import platform.StoreKit.SKStoreReviewController
import platform.UIKit.UIApplication
import platform.UIKit.UISceneActivationStateForegroundActive
import platform.UIKit.UIWindowScene

class AppleStoreReviewDataSource(
    private val params: ReviewInitParams,
) : ReviewDataSource {
    override fun requestReview(): Flow<ReviewResultCode> = flow {
        if (systemVersionMoreOrEqualThan("14.0")) {
            val scene = UIApplication.sharedApplication.connectedScenes.map { it as UIWindowScene }
                .first { it.activationState == UISceneActivationStateForegroundActive }
            SKStoreReviewController.requestReviewInScene(scene)
        } else {
            SKStoreReviewController.requestReview()
        }
        emit(ReviewResultCode.NO_ERROR)
    }

    override fun requestInStoreReview(): Flow<ReviewResultCode> = flow {
        val url =
            NSURL(string = "https://apps.apple.com/app/${params.appStoreId}?action=write-review")
        UIApplication.sharedApplication.openURL(url)
        emit(ReviewResultCode.NO_ERROR)
    }

}