package com.sanver.example.service

import com.sanver.example.service.response.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbAPI  {


    @GET("?type=movie")
    fun searchMovies(
        @Query("apikey") API_KEY: String,@Query(
            "t"
        ) title: String
    ): Observable<MovieResponse>

}
