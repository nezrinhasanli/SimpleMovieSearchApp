package com.nezrin.movieapp.model

data class MovieSearchResponse(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)