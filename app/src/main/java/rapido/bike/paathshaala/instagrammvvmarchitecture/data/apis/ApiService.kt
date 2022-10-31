package rapido.bike.paathshaala.instagrammvvmarchitecture.data.apis

import rapido.bike.paathshaala.instagrammvvmarchitecture.data.model.RetrofitBuilder

class ApiService {
    suspend fun getFeeds() = RetrofitBuilder.postService.getFeeds()
}