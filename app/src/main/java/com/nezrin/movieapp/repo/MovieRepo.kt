package com.nezrin.movieapp.repo

import com.nezrin.movieapp.model.MovieResponseByID
import com.nezrin.movieapp.model.MovieSearchResponse
import com.nezrin.movieapp.retrofit.WebServiceAPI
import retrofit2.Response

class MovieRepo(private val api: WebServiceAPI) {
//    private val api by lazy { APIUtils.instance }

    suspend fun getAllMoviesRepo(search:String): Response<MovieSearchResponse> {
        return api.getAllMovies(search)
    }
    suspend fun getMovieByIDRepo(id:String): Response<MovieResponseByID> {
        return api.getMovieByID(id=id)
    }
}