package com.example.artsy.ui.screens.MainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artsy.R
import com.example.artsy.ui.MainTopBar
import com.example.artsy.ui.navigation.NavDest
import com.example.artsy.ui.screens.Components.ErrorScreen
import com.example.compose.ArtsyTheme

// this will be the screen that is showcasing all artworks w kda

object MainScreenDestination: NavDest{
    override val route: String
        get() = "main"
    override val title: String
        get() = "Art Page"
}

// three states --> loading, success, error
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
//    navigateback: () -> Unit,  // not in use for now
    onNavigateUp: () -> Unit,
    title: String = "ArtClub",
    favouritesButton:Boolean = true,
    toFavourites: () -> Unit
){
    Scaffold(
        topBar = {
            MainTopBar(
            title = title,
            canNavigateBack = true,
            navigateUp= onNavigateUp,
            FavouritesButton = favouritesButton,
            toFavourites = toFavourites
        )}
    ) { innerPadding -> Card(modifier.padding(innerPadding)){
            ErrorScreen()
        }
    }

}

@Composable
fun ArtGrid(){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier.padding(horizontal = 4.dp),
    ) {
    TODO("When i put up the API thing, i will continue on this")
    }
}



@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    ArtsyTheme {
    }
}