package com.example.linguaapp.data.repository

import com.example.linguaapp.data.api.RetrofitInstance
import com.example.linguaapp.model.ApiResponse
import retrofit2.Response

class Repository {

    suspend fun getWordInfo(word: String): Response<ApiResponse>{
        return RetrofitInstance.api.getWord(word)
    }
}