package com.santimattius.kmp.skeleton.core.data.source

actual class ReviewSourceParams(val appStoreId: String)

actual fun getReviewDataSource(params: ReviewSourceParams): ReviewDataSource {
    return AppleStoreReviewDataSource(params)
}