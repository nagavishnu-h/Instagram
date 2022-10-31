package rapido.bike.paathshaala.instagrammvvmarchitecture.data.repository

import rapido.bike.paathshaala.instagrammvvmarchitecture.data.model.RetrofitBuilder

class FeedRepository {
    suspend fun getFeeds() = RetrofitBuilder.postService.getFeeds()
}