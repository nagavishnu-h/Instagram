package rapido.bike.paathshaala.instagrammvvmarchitecture.data.model

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import rapido.bike.paathshaala.instagrammvvmarchitecture.data.apis.FeedPosts
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "http://192.168.29.194:3000/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHttpClient())
            .build()
    }

    private fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
            )
            .build()

    val postService: FeedPosts = getRetrofit().create(FeedPosts::class.java)
}
