package com.nezrin.movieapp.retrofit

import com.nezrin.movieapp.model.MovieResponseByID
import com.nezrin.movieapp.model.MovieSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface WebServiceAPI {

    @GET("/")
    suspend fun getAllMovies(
        @Query("s") search: String,
        @Query("r") format: String = "json",
        @Query("page") page: Int = 1,
        @Header("X-RapidAPI-Key") key: String = "b9cfaf1421mshf0138a25bf64071p163975jsn48d2a007a87a",
        @Header("X-RapidAPI-Host") host: String = "movie-database-alternative.p.rapidapi.com"
    ):Response<MovieSearchResponse>

    @GET("/")
    suspend fun getMovieByID(
        @Query("r") format: String = "json",
        @Query("i") id: String,
        @Header("X-RapidAPI-Key") key: String = "b9cfaf1421mshf0138a25bf64071p163975jsn48d2a007a87a",
        @Header("X-RapidAPI-Host") host: String = "movie-database-alternative.p.rapidapi.com"
    ): Response<MovieResponseByID>

}