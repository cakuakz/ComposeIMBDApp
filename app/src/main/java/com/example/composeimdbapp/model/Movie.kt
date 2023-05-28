package com.example.composeimdbapp.model

data class Movie(
    val title: String,
    val rating: String,
    val description: String,
    val photo: Int,
    val count: Int? = null
)