package com.ulternativetechnology.kotlintestproject.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.ulternativetechnology.kotlintestproject.R

class OtherMainActivity : AppCompatActivity() {

    private val TAG = this.javaClass.simpleName
    private lateinit var viewModel: SimpleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other_main)

        val repository = Repository()
        val viewModelFactory = SimpleViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SimpleViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this) {
            if (it.isSuccessful) {
                Log.e(TAG, "Response - myUserId : ${it.body()?.myUserId}")
                Log.e(TAG, "Response - id : ${it.body()?.id}")
                Log.e(TAG, "Response - title : ${it.body()?.title}")
                Log.e(TAG, "Response - body : ${it.body()?.body}")
            }
        }
    }
}