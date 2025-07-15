package com.example.artsy.ui

import android.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.artsy.ui.navigation.NavigationHost


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    title:String = "No title",
    canNavigateBack:Boolean,
    scrollBehavior: TopAppBarScrollBehavior?=null,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    FavouritesButton:Boolean = false,
    toFavourites : () -> Unit
){
    CenterAlignedTopAppBar(
        title = { Text(title, color = MaterialTheme.colorScheme.onSecondary)},
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            titleContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Filled.ArrowBack,
                        contentDescription = "Back Button",
                        tint = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        },
        actions = {
            if(FavouritesButton){
                IconButton(onClick = {toFavourites()}) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSecondary

                    )
                }
            }
        }
    )
}

@Composable
fun ArtGalleryApp(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier){
    NavigationHost(navController)
}