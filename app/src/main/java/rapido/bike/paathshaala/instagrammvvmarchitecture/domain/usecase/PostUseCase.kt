package rapido.bike.paathshaala.instagrammvvmarchitecture.domain.usecase

import rapido.bike.paathshaala.instagrammvvmarchitecture.data.model.FeedResponse
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.model.PostCard
import rapido.bike.paathshaala.instagrammvvmarchitecture.data.repository.FeedRepository
import rapido.bike.paathshaala.instagrammvvmarchitecture.utils.Resource
import retrofit2.Response

class PostUseCase {
    private val feedRepository = FeedRepository()
    suspend fun getFeeds(): Resource<List<PostCard>> {
        val response = feedRepository.getFeeds()
        return if (response.isSuccessful) {
           return if (validateResponse(response)) {
                response.body()?.let { Resource.Success(it.data) }
                    ?: Resource.Failure("No data found")
            } else {
                Resource.Failure("No data found")
            }
        }
        else {
            Resource.Failure("Something went wrong")
        }
    }

    private fun validateResponse(response: Response<FeedResponse>): Boolean {
        return !response.body()?.data.isNullOrEmpty()
    }
}