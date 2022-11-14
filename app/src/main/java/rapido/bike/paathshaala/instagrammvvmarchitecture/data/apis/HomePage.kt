package rapido.bike.paathshaala.instagrammvvmarchitecture.data.apis

import rapido.bike.paathshaala.instagrammvvmarchitecture.data.model.FeedResponse
import rapido.bike.paathshaala.instagrammvvmarchitecture.data.model.StoryResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomePage {
    @GET("instagramFeed")
    suspend fun getFeeds(): Response<FeedResponse>

    @GET("storyFeed")
    suspend fun getStories(): Response<StoryResponse>
}