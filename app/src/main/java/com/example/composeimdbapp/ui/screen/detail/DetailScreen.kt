package com.example.composeimdbapp.ui.screen.detail

import android.content.Context
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.composeimdbapp.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composeimdbapp.ViewModelFactory
import com.example.composeimdbapp.di.Injection
import com.example.composeimdbapp.model.Movie
import com.example.composeimdbapp.ui.common.UiState
import com.example.composeimdbapp.ui.theme.ComposeIMDBAppTheme

// Detail Screen
@Composable
fun DetailScreen(
    movieDetail: String,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->  
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getMoviesDetail(movieDetail)
            }
            is UiState.Success -> {
                val data = uiState.data
                MovieDetail(
                    data.photo,
                    data.title,
                    data.rating,
                    data.description,
                    onBackClick = navigateBack,
                )
            }
            is UiState.Error -> {}
        }
    }
}




//Detail Content
@Composable
fun MovieDetail(
    @DrawableRes photo: Int,
    title: String,
    rating: String,
    description: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colors.primaryVariant)
            .fillMaxHeight()
            .padding(4.dp)
    ) {

            Row(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                Box(modifier = Modifier) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Arrow",
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { onBackClick() }
                    )
                }
            }


            Row(
                modifier = Modifier
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(photo),
                        contentDescription = "Movie Photo",
                        modifier = Modifier
                            .size(300.dp)
                            .padding(5.dp)
                    )
                    Text(
                        text = title,
                        fontSize = (36.sp),
                        fontFamily = FontFamily.SansSerif,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                    )
                    Text(
                        text = rating,
                        fontSize = (18.sp),
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                    )
                    Text(
                        text = description,
                        fontSize = (14.sp),
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier
                            .padding(5.dp)
                    )
                }
            }
        }
    }


@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun MovieDetailPreview(){
    ComposeIMDBAppTheme {
        MovieDetail(
            R.drawable.forrest_gump,
            "Guardian of the Galaxy Vol. 2",
            "10/10",
            "lorem lorem ipsum ipsum blablabla",
            onBackClick = {},
        )
    }
}