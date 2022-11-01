package rapido.bike.paathshaala.instagrammvvmarchitecture.data.repository

import rapido.bike.paathshaala.instagrammvvmarchitecture.data.apis.FeedPosts
import retrofit2.Retrofit
import javax.inject.Inject

class FeedRepository @Inject constructor(retrofit: Retrofit){
    private val feedService = retrofit.create(FeedPosts::class.java)
    suspend fun getFeeds() = feedService.getFeeds()
}