package rapido.bike.paathshaala.instagrammvvmarchitecture.model

data class FeedResponse(
    val status:String,
    val data: List<PostCard>,
    val message: String
)
