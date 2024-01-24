package com.santimattius.kmp.skeleton.core.data.source

actual class ReviewInitParams(val appStoreId: String)

actual fun getReviewDataSource(params: ReviewInitParams): ReviewDataSource {
    return AppleStoreReviewDataSource(params)
}