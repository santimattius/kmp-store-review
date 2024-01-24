package com.santimattius.kmp.skeleton.core.data.source

import android.app.Activity

actual class ReviewSourceParams(val activity: Activity)

actual fun getReviewDataSource(params: ReviewSourceParams): ReviewDataSource {
    return GooglePlayReviewDataSource(params)
}