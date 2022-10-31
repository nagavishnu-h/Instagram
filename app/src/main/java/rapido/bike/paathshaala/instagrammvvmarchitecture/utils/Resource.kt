package rapido.bike.paathshaala.instagrammvvmarchitecture.utils

sealed class Resource<out T> {
    data class Success<out R>(val data: R) : Resource<R>()
    data class Failure(val message: String?) : Resource<Nothing>()
    data class Loading(val isLoading: Boolean) : Resource<Nothing>()
}