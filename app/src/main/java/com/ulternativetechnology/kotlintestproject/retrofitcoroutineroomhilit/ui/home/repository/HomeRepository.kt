package com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.ui.home.repository

import com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.data.MovieAppService
import com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.data.model.MoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val movieAppService: MovieAppService
) {
    suspend fun fetchPopular(apikey: String): Response<MoviesResponse> = withContext(
        Dispatchers.IO) {
        val popular = movieAppService.getPopularMovies(apikey)
        popular
    }
}