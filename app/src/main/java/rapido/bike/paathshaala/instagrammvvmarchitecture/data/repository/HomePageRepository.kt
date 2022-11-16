package rapido.bike.paathshaala.instagrammvvmarchitecture.data.repository

import rapido.bike.paathshaala.instagrammvvmarchitecture.data.apis.HomePage
import retrofit2.Retrofit
import javax.inject.Inject

class HomePageRepository @Inject constructor(retrofit: Retrofit){
    private val homePageService = retrofit.create(HomePage::class.java)
    suspend fun getFeeds() = homePageService.getFeeds()
    suspend fun getStories() = homePageService.getStories()
}