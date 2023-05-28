package com.example.composeimdbapp.data

import com.example.composeimdbapp.model.Movie
import com.example.composeimdbapp.model.MoviesData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MoviesRepository {

    private val movies = mutableListOf<Movie>()

    init {
        if (movies.isEmpty()) {
            MoviesData.movies.forEach { movieData ->
                movies.add(Movie(
                    movieData.title,
                    movieData.rating,
                    movieData.description,
                    movieData.photo,
                    movieData.count
                ))
            }
        }
    }

    fun getAllMovies(): Flow<List<Movie>> {
        return flowOf(movies)
    }

    fun getMoviesDetail(movieDetail: String): Movie {
        return movies.first {
            it.title == movieDetail
        }
    }

    companion object {
        @Volatile
        private var instance: MoviesRepository? = null

        fun getInstance(): MoviesRepository =
            instance ?: synchronized(this) {
                MoviesRepository().apply {
                    instance = this
                }
            }
    }
}