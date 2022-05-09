package com.ulternativetechnology.kotlintestproject.retrofit

import retrofit2.Response

class Repository {
    suspend fun getPost(): Response<Post> = RetrofitInstance.api.getPost()
}