package com.santimattius.kmp.skeleton.core.data.source

import kotlinx.coroutines.flow.Flow

interface ReviewDataSource {
    fun init() {}
    fun requestReview(): Flow<ReviewResultCode>
    fun requestInStoreReview(): Flow<ReviewResultCode>
}