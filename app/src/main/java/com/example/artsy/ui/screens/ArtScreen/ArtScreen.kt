package com.example.artsy.ui.screens.ArtScreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.artsy.R
import com.example.artsy.ui.MainTopBar
import com.example.artsy.ui.navigation.NavDest
import com.example.artsy.ui.theme.Heart
import com.example.compose.ArtsyTheme



object ArtScreenDestination: NavDest{

    override val route: String
        get() = "artDetails"
    override val title: String
        get() = "Art Details"
    const val itemIdArg = "itemId"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtScreen(
    artId:Int,
    artViewModel: ArtScreenViewModel = viewModel(factory = ArtScreenViewModel.Factory),
    onNavigateUp: () ->Unit
){

    val artPiece = artViewModel.artPiece.collectAsState()
    LaunchedEffect(artId) { artViewModel.fetchArtPiecebyId(artId) }

    var isanimatedBackgroundColor by rememberSaveable {mutableStateOf(false)}


    val animatedColor by animateColorAsState(
        if (isanimatedBackgroundColor) MaterialTheme.colorScheme.onSecondary else Color.Red, label = "color")


    Scaffold(
        topBar = {
            MainTopBar(
                canNavigateBack = true,
                navigateUp = onNavigateUp,
                toFavourites = {},
                title = "${artPiece.value?.title}"
            )
        }
    ) { innerPadding ->
        Card(modifier = Modifier.padding(innerPadding)){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current).data(artPiece.value?.images?.web?.url)
                        .crossfade(true).build(),
                    error = painterResource(R.drawable.ic_connection_error),
                    placeholder = painterResource(R.drawable.loading_img),
                    contentDescription = artPiece.value?.tombstone,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.
                    padding(16.dp)
                )

                Row{
                    artPiece.value?.title?.let {
                        Text(
                            it,
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    IconButton(
                        onClick = {
                            if(!isanimatedBackgroundColor){
                                artPiece.value?.let{artViewModel.AddPiecetoFavourites(it)}
                            }
                            else{
                                artPiece.value?.let{artViewModel.RemovefromFavourites(it)}
                            }
                            isanimatedBackgroundColor = !isanimatedBackgroundColor

                        }
                    ) {
                        Icon(
                            imageVector = Heart,
                            contentDescription = "Add to Favourites Button",
                            tint = animatedColor
                        )
                    }


                }


                artPiece.value?.description?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}



//
//@Preview(showBackground = true)
//@Composable
//fun ArtScreenPreview(){
//    ArtsyTheme(){
////        var fake = fakeArtPiece()
////        ArtScreen(160729)
//    }
//}
