package com.example.artsy.ui.screens.HomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artsy.ui.navigation.NavDest
import com.example.compose.ArtsyTheme


object HomeScreenDestination: NavDest{
    override val route: String
        get() = "home"
    override val title: String
        get() = "Home Page"

}


@Composable
fun HomeScreen(
    navigateToEntry: () -> Unit
){  // this will be the main screen

    Card(
        modifier = Modifier
            .fillMaxSize(),

        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        )

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text("Welcome to ArtClub",
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(8.dp)
            )
            Button(onClick ={
                navigateToEntry()
            },
                modifier = Modifier){
                Text("Start your journey")
            }
        }
    }

}




@Preview(showBackground = true )
@Composable


fun HomeScreenPreview(){
    ArtsyTheme{

    }
}