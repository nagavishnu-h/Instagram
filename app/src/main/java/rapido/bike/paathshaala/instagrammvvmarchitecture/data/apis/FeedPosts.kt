package rapido.bike.paathshaala.instagrammvvmarchitecture.data.apis

import rapido.bike.paathshaala.instagrammvvmarchitecture.data.model.FeedResponse
import retrofit2.Response
import retrofit2.http.GET

interface FeedPosts {
    @GET("instagramFeed")
    suspend fun getFeeds(): Response<FeedResponse>
}