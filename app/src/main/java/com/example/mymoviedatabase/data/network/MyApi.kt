package com.example.mymoviedatabase.data.network

import com.example.mymoviedatabase.data.network.responses.TopRatedResponse
import com.example.mymoviedatabase.data.network.responses.UpcomingResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MyApi {

    @GET("top_rated?api_key=a543b01c1ea28b7465f942f1d8ac84d7&language=en-US&page=1")
    suspend fun getTopRated() : Response<TopRatedResponse>

    @GET("upcoming?api_key=a543b01c1ea28b7465f942f1d8ac84d7&language=en-US&page=1")
    suspend fun getUpcoming() : Response<UpcomingResponse>

    companion object{
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : MyApi{

            val okkHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okkHttpclient)
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}