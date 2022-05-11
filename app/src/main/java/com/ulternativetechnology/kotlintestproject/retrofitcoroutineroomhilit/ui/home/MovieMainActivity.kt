package com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ulternativetechnology.kotlintestproject.BuildConfig
import com.ulternativetechnology.kotlintestproject.R
import com.ulternativetechnology.kotlintestproject.databinding.ActivityMovieMainBinding
import com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.data.model.Movie
import com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.ui.home.adapter.HomeAdapter
import com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.ui.home.adapter.RecyclerViewHomeClickListener
import com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.ui.home.viewmodel.HomeViewModel
import com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.util.Resource
import com.ulternativetechnology.kotlintestproject.retrofitcoroutineroomhilit.util.contentView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieMainActivity : AppCompatActivity(), RecyclerViewHomeClickListener {

    private val binding: ActivityMovieMainBinding by contentView(R.layout.activity_movie_main)
    private val homeViewModel: HomeViewModel by viewModels()
    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(this, this@MovieMainActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_main)

        binding.run {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR          //  set status text dark
            window.statusBarColor = ContextCompat.getColor(applicationContext,R.color.white)    // set status background white

            recyclerView.apply {
                adapter = homeAdapter
            }
        }

        homeViewModel.fetchPopular(BuildConfig.MOVIE_DB_API_TOKEN)
        observeUI()
    }

    private fun observeUI() {
        homeViewModel.moviePopular.observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    val data = it.data!!.movies
                    homeAdapter.submitList(data!!)
                }
                is Resource.Error -> {
                    binding.progress.visibility = View.GONE
                    it.message?.let { message ->
                        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    binding.progress.visibility = View.VISIBLE
                }
            }

        }
    }


    override fun clickOnItem(data: Movie, card: View) {
        //
    }
}