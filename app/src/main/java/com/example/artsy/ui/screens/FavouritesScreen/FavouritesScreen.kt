package com.example.artsy.ui.screens.FavouritesScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.example.artsy.ui.MainTopBar
import com.example.artsy.ui.navigation.NavDest
import com.example.artsy.ui.screens.Components.ErrorScreen
import com.example.artsy.ui.screens.Components.LoadingScreen
import com.example.artsy.ui.theme.Heart
import com.example.compose.ArtsyTheme


object FavouritesScreenDestination: NavDest {
    override val route: String
        get() = "favs"
    override val title: String
        get() = "User Favourites"
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritesScreen(
    modifier: Modifier = Modifier,
    title:String = "Favourites",
    OnNavigateUp: ()-> Unit,
    canNavigateBack: Boolean = true){
    Scaffold(
        topBar = {MainTopBar(
            title = title,
            canNavigateBack = canNavigateBack,
            navigateUp = OnNavigateUp,
            toFavourites = {} // no implementation because there is no button
        )}
    )
    { innerPadding ->
        Card(modifier.padding(innerPadding)){
            EmptyFavouritesScreen()
        }
    }
}


@Composable
fun EmptyFavouritesScreen(modifier: Modifier = Modifier){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer),

    ){
        Icon(
            imageVector = Heart,
            contentDescription = "No Favourites",
            modifier = modifier.size(100.dp),
            tint = MaterialTheme.colorScheme.onPrimaryContainer

        )
        Text("No Favourites", style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Preview(showBackground = true)
@Composable
fun tempPreview(){
    ArtsyTheme {
        EmptyFavouritesScreen()
    }
}

