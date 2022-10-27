package rapido.bike.paathshaala.instagrammvvmarchitecture.apis

import rapido.bike.paathshaala.instagrammvvmarchitecture.model.RetrofitBuilder

class ApiService {
    suspend fun getFeeds() = RetrofitBuilder.postService.getFeeds()
}