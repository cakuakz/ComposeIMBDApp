package com.example.composeimdbapp.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composeimdbapp.R
import com.example.composeimdbapp.ui.theme.ComposeIMDBAppTheme


// Profile Screen
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.primary)
            .padding(3.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                tint = MaterialTheme.colors.secondary,
                contentDescription = "Back Arrow",
                modifier = Modifier
                    .padding(16.dp)
                    .clickable { onBackClick() }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.rafi_profil),
                    contentDescription = "profil",
                    modifier = Modifier
                        .padding(4.dp)
                )

                Text(
                    text = "Mohamad Rafi Irfansyah",
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.primaryVariant,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(4.dp)
                )
                Text(
                    text = "a038dkx4439@bangkit.academy",
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colors.secondary,
                    modifier = Modifier
                        .padding(4.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    ComposeIMDBAppTheme {
        ProfileScreen(
            onBackClick = {}
        )
    }
}
