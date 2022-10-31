package rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.usecase.PostUseCase
import rapido.bike.paathshaala.instagrammvvmarchitecture.utils.Resource

class FeedViewModel() : ViewModel() {
    private val postUseCase = PostUseCase()

    fun getFeeds() = liveData(Dispatchers.IO) {
        emit(Resource.Loading(true))
        emit(postUseCase.getFeeds())
    }
}