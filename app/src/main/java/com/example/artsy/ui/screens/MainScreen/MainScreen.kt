package com.example.artsy.ui.screens.MainScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.artsy.R
import com.example.artsy.data.model.ArtPiece
import com.example.artsy.ui.MainTopBar
import com.example.artsy.ui.navigation.NavDest
import com.example.artsy.ui.screens.ArtScreen.ArtScreenDestination
import com.example.artsy.ui.screens.Components.ErrorScreen
import com.example.artsy.ui.screens.Components.LoadingScreen
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
    toFavourites: () -> Unit,
    navController: NavController
){

    val mainScreenViewModel: MainScreenViewModel = viewModel(factory = MainScreenViewModel.Factory)
    val mainScreenUIstate = mainScreenViewModel.artUiState
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
            when(mainScreenUIstate){
            is MainArtUiState.Loading -> { LoadingScreen() }
            is MainArtUiState.Success -> {ArtGrid(
                photos =  mainScreenUIstate.photos,
                navController
            )}
            is MainArtUiState.Error -> {ErrorScreen()}
            }

        }
    }

}

@Composable
fun ArtGrid(
    photos: List<ArtPiece>,
    navController: NavController
){
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier.padding(horizontal = 4.dp),
    ) {
        items(items = photos, key = { photo -> photo.id }) { photo ->
            PhotoCard(
                photo,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .clickable(
                        onClick = {navController.navigate("${ArtScreenDestination.route}/${photo.id}")}
                    )

            )
        }
    }
}


@Composable
fun PhotoCard(
    photo: ArtPiece,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(photo.images?.web?.url)
                .crossfade(true).build(),
            error = painterResource(R.drawable.ic_connection_error),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = "photo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }

}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    ArtsyTheme {
    }
}