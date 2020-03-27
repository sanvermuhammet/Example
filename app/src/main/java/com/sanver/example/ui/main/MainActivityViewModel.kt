package com.sanver.example.ui.main

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.sanver.example.App
import com.sanver.example.BuildConfig
import com.sanver.example.core.BaseViewModel
import com.sanver.example.core.Resource
import com.sanver.example.service.response.MovieResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel(app: Application) : BaseViewModel(app) {

    init {
        (app as? App)?.component?.inject(this)
    }

    val getMoviesLiveData = MutableLiveData<Resource<MovieResponse>>()
    val tvAlertLiveData = ObservableField<Boolean>(true)

    val item = ObservableField<MovieResponse>()

    var position = -1

    fun setModel(item: MovieResponse, position: Int) {
        this.item.set(item)
        this.position = position
    }


    fun searchMovies(query: String?) {
        disposable.add(baseApi.searchMovies(BuildConfig.API_TOKEN, query!!)
            .subscribeOn(Schedulers.io())
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .doOnSubscribe { progressLiveData.postValue(true) }
            .doOnTerminate { progressLiveData.postValue(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (!it.data?.response.equals("False")) {
                    getMoviesLiveData.postValue(it)
                    tvAlertLiveData.set(false)
                } else {
                    tvAlertLiveData.set(true)
                }
            })
    }
}