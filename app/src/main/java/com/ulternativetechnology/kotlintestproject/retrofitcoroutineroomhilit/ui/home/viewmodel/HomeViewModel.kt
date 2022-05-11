package com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.ui.home.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.data.model.MoviesResponse
import com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.ui.home.repository.HomeRepository
import com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.util.Resource
import com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.util.hasInternetConnection
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {
    val moviePopular: MutableLiveData<Resource<MoviesResponse>> = MutableLiveData()

    fun fetchPopular(apikey: String) {
        // 뷰모델 코루틴이 실행되기 전엔 로딩 중이라는 sealed class의 자식 클래스를 넣는
        moviePopular.postValue(Resource.Loading())
        viewModelScope.launch {
            try {
                if (hasInternetConnection(context)) {
                    val response = homeRepository.fetchPopular(apikey)
                    moviePopular.postValue(Resource.Success(response.body()!!))
                } else
                    moviePopular.postValue(Resource.Error("No Internet Connection"))
            } catch (e: Exception) {
                when (e) {
                    is IOException -> moviePopular.postValue(Resource.Error("Network Failure : " + e.localizedMessage))
                    else -> moviePopular.postValue(Resource.Error("Conversion Error"))
                }
            }
        }
    }

}