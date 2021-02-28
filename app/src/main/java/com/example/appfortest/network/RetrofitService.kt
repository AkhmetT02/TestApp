package com.example.appfortest.network

import com.example.appfortest.models.Result
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("books/v1/volumes")
    fun getBooks(@Query("q") query: String, @Query("key") apiKey: String): Call<Result>
}