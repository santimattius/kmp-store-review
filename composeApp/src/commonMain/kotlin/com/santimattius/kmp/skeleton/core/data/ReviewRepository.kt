package com.santimattius.kmp.skeleton.core.data

import com.santimattius.kmp.skeleton.core.data.source.ReviewDataSource

class ReviewRepository(
    private val dataSource: ReviewDataSource,
) {

    fun requestReview() = dataSource.requestReview()
    fun requestInStoreReview() = dataSource.requestInStoreReview()

}