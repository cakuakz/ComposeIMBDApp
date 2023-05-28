package com.example.composeimdbapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composeimdbapp.model.MoviesData
import com.example.composeimdbapp.navigation.Screen
import com.example.composeimdbapp.ui.screen.detail.DetailScreen
import com.example.composeimdbapp.ui.screen.home.HomeListScreen
import com.example.composeimdbapp.ui.screen.home.MoviesList
import com.example.composeimdbapp.ui.screen.profile.ProfileScreen
import com.example.composeimdbapp.ui.theme.ComposeIMDBAppTheme


@Composable
fun MoviesApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == Screen.Home.route) {
                MyTopBar(navController)
            }
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            NavHost(
                navController = navController,
                startDestination = Screen.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Home.route) {
                    HomeListScreen(
                        navigateToDetail = { movieDetail ->
                            navController.navigate(Screen.MovieDetail.createRoute(movieDetail))
                        }
                    )
                }
                composable(
                    route = Screen.MovieDetail.route,
                    arguments = listOf(navArgument("movieDetail") { type = NavType.StringType }),
                ) {
                    val title = it.arguments?.getString("movieDetail") ?: -1L
                    DetailScreen(
                        movieDetail = title as String,
                        navigateBack = {
                            navController.navigateUp()
                        },
                    )
                }
                composable(Screen.About.route) {
                    ProfileScreen(
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesAppPreview() {
    ComposeIMDBAppTheme {
        MoviesApp()
    }
}



@Composable
fun MyTopBar(
    navController: NavHostController,
) {
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = "IMDB",
                fontWeight = FontWeight.SemiBold
            )
        },
        actions = {
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More")
            }
            DropdownMenu(
                expanded = showMenu,
                onDismissRequest = { showMenu = false }
            ) {
                DropdownMenuItem(onClick = {
                    navController.navigate(Screen.About.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                }) {
                    Text(text = "About")
                }
            }
        }
    )
}




