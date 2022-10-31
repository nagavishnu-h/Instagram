package rapido.bike.paathshaala.instagrammvvmarchitecture.data.model

import rapido.bike.paathshaala.instagrammvvmarchitecture.domain.model.PostCard

data class FeedResponse(
    val status:String,
    val data: List<PostCard>,
    val message: String
)
