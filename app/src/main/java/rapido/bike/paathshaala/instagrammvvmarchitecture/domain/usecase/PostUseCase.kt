package rapido.bike.paathshaala.instagrammvvmarchitecture.domain.usecase

import rapido.bike.paathshaala.instagrammvvmarchitecture.data.model.FeedResponse
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.model.PostCard
import rapido.bike.paathshaala.instagrammvvmarchitecture.data.repository.HomePageRepository
import rapido.bike.paathshaala.instagrammvvmarchitecture.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class PostUseCase @Inject constructor(private val homePageRepository: HomePageRepository) {
    suspend operator fun invoke() : Resource<List<PostCard>>{
        val response = homePageRepository.getFeeds()
        return if (response.isSuccessful) {
            return if (validateResponse(response)) {
                response.body()?.let { Resource.Success(it.data) }
                    ?: Resource.Failure("No posts found")
            } else {
                Resource.Failure("No posts found")
            }
        } else {
            Resource.Failure("Something went wrong while fetching feeds.")
        }
    }

    private fun validateResponse(response: Response<FeedResponse>): Boolean {
        return response.body()?.data?.isNotEmpty() ?: false
    }
}