package rapido.bike.paathshaala.instagrammvvmarchitecture.apis

import rapido.bike.paathshaala.instagrammvvmarchitecture.model.FeedResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface FeedPosts {
    @GET("instagramFeed")
    suspend fun getFeeds(): Response<FeedResponse>
}