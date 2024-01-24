package com.santimattius.kmp.skeleton.core.data.source

import android.app.Activity

actual class ReviewInitParams(val activity: Activity)

actual fun getReviewDataSource(params: ReviewInitParams): ReviewDataSource {
    return GooglePlayReviewDataSource(params)
}