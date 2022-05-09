package com.ulternativetechnology.kotlintestproject.retrofit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/* 뷰모델에서 파라미터로 Repository를 받아야 하기 때문에 생성한 Factory Class */
class SimpleViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = SimpleViewModel(repository) as T
}