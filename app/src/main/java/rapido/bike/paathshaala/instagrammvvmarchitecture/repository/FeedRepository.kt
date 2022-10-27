package rapido.bike.paathshaala.instagrammvvmarchitecture.repository

import rapido.bike.paathshaala.instagrammvvmarchitecture.apis.ApiService

class FeedRepository {
    private val apiService = ApiService()
    suspend fun getFeeds() = apiService.getFeeds()
}