package rapido.bike.paathshaala.instagrammvvmarchitecture.domain.usecase

import rapido.bike.paathshaala.instagrammvvmarchitecture.data.model.StoryResponse
import rapido.bike.paathshaala.instagrammvvmarchitecture.data.repository.HomePageRepository
import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.model.StoryCard
import rapido.bike.paathshaala.instagrammvvmarchitecture.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class StoryUseCase @Inject constructor(private val homePageRepository: HomePageRepository) {
    suspend operator fun invoke() : Resource<List<StoryCard>> {
        val response = homePageRepository.getStories()
        return if (response.isSuccessful) {
            return if (validateResponse(response)) {
                response.body()?.let { Resource.Success(it.data) }
                    ?: Resource.Failure("No stories found")
            } else {
                Resource.Failure("No stories found")
            }
        } else {
            Resource.Failure("Something went wrong while fetching stories")
        }
    }

    private fun validateResponse(response: Response<StoryResponse>): Boolean {
        return response.body()?.data?.isNotEmpty() ?: false
    }
}