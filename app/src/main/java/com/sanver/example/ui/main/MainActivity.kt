package com.sanver.example.ui.main

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.Observer
import com.sanver.example.R
import com.sanver.example.core.BaseActivity
import com.sanver.example.core.Resource
import com.sanver.example.databinding.ActivityMainBinding
import com.sanver.example.service.response.MovieResponse
import com.sanver.example.ui.main.adapter.MovieAdapter

class MainActivity :
    BaseActivity<MainActivityViewModel, ActivityMainBinding>(MainActivityViewModel::class.java) {
    override fun initViewModel(viewModel: MainActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun getLayoutRes() = R.layout.activity_main

    override fun init() {
        super.init()
        binding.searchView.setOnQueryChangeListener { oldQuery, newQuery ->
            binding.rvMovies.smoothScrollToPosition(0)
            if (newQuery.isNotEmpty()) {
                viewModel.getMoviesLiveData.value?.data?.response="False"
                getMovies(newQuery)
            }else{

                binding.tvAlertText.text=""
                viewModel.tvAlertLiveData.set(true)
            }

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
    }

    private fun getMovies(newQuery: String?) {
        viewModel.searchMovies(newQuery)

        if (viewModel.getMoviesLiveData.hasActiveObservers())
            viewModel.getMoviesLiveData.removeObservers(this)




        binding.rvMovies.adapter = MovieAdapter { item, position ->
        }


        viewModel.getMoviesLiveData.observe(this@MainActivity, Observer<Resource<MovieResponse>> {
            it.let {
                if(it.data?.response.equals("False",true))
                    binding.tvAlertText.text=getString(R.string.alert_movie)


                val list = mutableListOf<MovieResponse>()
                list.add(it.data!!)
                (binding.rvMovies.adapter as MovieAdapter).submitList(list as List<MovieResponse?>?)
            }
        })


    }



}
