package com.example.linguaapp.data.api

import com.example.linguaapp.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("entries/en/{word}")
    fun getWord(@Path("word") word: String): Response<ApiResponse>
}