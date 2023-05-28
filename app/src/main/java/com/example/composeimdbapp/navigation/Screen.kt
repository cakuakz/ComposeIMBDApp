package com.example.composeimdbapp.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail")
    object About : Screen("about")
    object MovieDetail : Screen("home/{movieDetail}") {
        fun createRoute(movieDetail: String) = "home/$movieDetail"
    }
}