package com.example.composeimdbapp.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeimdbapp.ViewModelFactory
import com.example.composeimdbapp.di.Injection
import com.example.composeimdbapp.model.Movie
import com.example.composeimdbapp.ui.common.UiState

// Home Screen
@Composable
fun HomeListScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeListViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (String) -> Unit,
) {

    val (searchQuery, setSearchQuery) = remember { mutableStateOf("") }

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllMovies()
            }
            is UiState.Success -> {
                Column(
                    modifier = Modifier
                        .background(color = MaterialTheme.colors.primary)
                ) {
                    SearchBar(onSearch = setSearchQuery)
                    Spacer(modifier = Modifier.height(8.dp))
                    MoviesList(
                        movies = uiState.data,
                        modifier = modifier,
                        navigateToDetail = navigateToDetail,
                        searchQuery = searchQuery
                    )
                }
            }
            is UiState.Error -> {}
        }
    }
}


// movie lists
@Composable
fun MoviesList(
    movies: List<Movie>,
    modifier: Modifier = Modifier,
    searchQuery: String,
    navigateToDetail: (String) -> Unit,
) {

    val filteredMovies = if (searchQuery.isNotEmpty()) {
        movies.filter { movie ->
            movie.title.contains(searchQuery, ignoreCase = true)
        }
    } else {
        movies
    }

    Box(
        modifier = modifier
            .background(color = MaterialTheme.colors.primary)
    ) {
        LazyColumn {
            items(filteredMovies) {
                MovieListItem(
                    title = it.title,
                    rating = it.rating,
                    photo = it.photo,
                    modifier = Modifier
                        .clickable {
                            navigateToDetail(it.title)
                        }
                        .fillMaxWidth(),
                    navigateToDetail = navigateToDetail
                )
            }
        }
    }
}



// movie lists item
@Composable
fun MovieListItem(
    title: String,
    rating: String,
    modifier: Modifier = Modifier,
    photo: Int,
    navigateToDetail: (String) -> Unit,
) {
    // card list item
    Card(
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {

        // movie row item
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primaryVariant)
                .clickable(onClick = {
                    navigateToDetail(title)
                })
                .padding(7.dp),
        ) {
            Image(
                painter = painterResource(photo),
                contentDescription = "movie",
                modifier = Modifier
                    .size(100.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif
                )
                Text(
                    text = rating,
                    fontSize = 14.sp
                )
            }
        }
    }
}



@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    val searchQuery = remember { mutableStateOf("") }

    TextField(
        value = searchQuery.value,
        onValueChange = { query ->
            searchQuery.value = query
            onSearch(query)
        },
        label = { Text("Search") },
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .background(color = MaterialTheme.colors.secondary)
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(MaterialTheme.colors.primaryVariant),

    )
}