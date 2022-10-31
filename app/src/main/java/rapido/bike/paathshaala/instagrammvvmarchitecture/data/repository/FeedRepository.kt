package rapido.bike.paathshaala.instagrammvvmarchitecture.data.repository

import rapido.bike.paathshaala.instagrammvvmarchitecture.data.apis.ApiService

class FeedRepository {
    private val apiService = ApiService()
    suspend fun getFeeds() = apiService.getFeeds()
}