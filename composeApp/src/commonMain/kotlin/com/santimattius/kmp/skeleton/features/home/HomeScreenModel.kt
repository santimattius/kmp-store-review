package com.santimattius.kmp.skeleton.features.home

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.santimattius.kmp.skeleton.core.data.PictureRepository
import com.santimattius.kmp.skeleton.core.data.ReviewRepository
import com.santimattius.kmp.skeleton.core.domain.Picture
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class HomeUiState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val data: Picture? = null,
)

class HomeScreenModel(
    private val pictureRepository: PictureRepository,
    private val reviewRepository: ReviewRepository,
) : StateScreenModel<HomeUiState>(HomeUiState()) {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        mutableState.update { it.copy(isLoading = false, hasError = true) }
    }

    init {
        randomImage()
    }

    fun randomImage() {
        mutableState.update { it.copy(isLoading = true) }
        screenModelScope.launch(exceptionHandler) {
            pictureRepository.random().onSuccess { picture ->
                mutableState.update { it.copy(isLoading = false, data = picture) }
            }.onFailure {
                mutableState.update { it.copy(isLoading = false, hasError = true) }
            }
        }
    }

    fun launchReview(){
        screenModelScope.launch(exceptionHandler) {
            reviewRepository.requestReview().collectLatest {

            }
        }
    }
}