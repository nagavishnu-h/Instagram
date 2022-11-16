package rapido.bike.paathshaala.instagrammvvmarchitecture.data.model

import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.model.StoryCard

data class StoryResponse(
    val status: String,
    val data: List<StoryCard>,
    val message: String
)
