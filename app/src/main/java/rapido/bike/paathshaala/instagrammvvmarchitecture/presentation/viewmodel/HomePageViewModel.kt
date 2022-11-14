package rapido.bike.paathshaala.instagrammvvmarchitecture.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.usecase.PostUseCase
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.usecase.StoryUseCase
import rapido.bike.paathshaala.instagrammvvmarchitecture.utils.Resource
import javax.inject.Inject

class HomePageViewModel @Inject constructor(private val postUseCase: PostUseCase, private val storyUseCase: StoryUseCase) : ViewModel(){
    fun getFeeds() = liveData(Dispatchers.IO) {
        emit(Resource.Loading(true))
        emit(postUseCase())
    }

    fun getStories() = liveData(Dispatchers.IO) {
        emit(Resource.Loading(true))
        emit(storyUseCase())
    }
}