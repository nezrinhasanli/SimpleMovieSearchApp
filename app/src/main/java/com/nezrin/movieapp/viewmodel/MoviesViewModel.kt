package com.nezrin.movieapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nezrin.movieapp.model.MovieResponseByID
import com.nezrin.movieapp.model.Search
import com.nezrin.movieapp.repo.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repo: MovieRepo):ViewModel() {

//    private val repo by lazy { MovieRepo() }

    var movieList= MutableLiveData<List<Search>>()
    var movieId= MutableLiveData<MovieResponseByID>()


    fun getAllMoviesVM(search: String){
        viewModelScope.launch {
            val response=repo.getAllMoviesRepo(search)
            if (response.isSuccessful){
                val body=response.body()
                movieList.postValue(body?.Search)
            }
        }
    }
    fun getMovieByIDVM(id:String){
        viewModelScope.launch {
            val response=repo.getMovieByIDRepo(id)
            if (response.isSuccessful){
                val body=response.body()
                movieId.postValue(body!!)
            }
        }
    }
}