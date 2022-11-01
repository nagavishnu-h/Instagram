package rapido.bike.paathshaala.instagrammvvmarchitecture.data.repository

import rapido.bike.paathshaala.instagrammvvmarchitecture.data.model.RetrofitBuilder
import javax.inject.Inject

class FeedRepository @Inject constructor(){
    suspend fun getFeeds() = RetrofitBuilder.postService.getFeeds()
}