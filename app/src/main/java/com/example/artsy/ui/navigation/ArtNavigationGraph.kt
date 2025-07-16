package com.example.artsy.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.artsy.ui.screens.ArtScreen.ArtScreen
import com.example.artsy.ui.screens.ArtScreen.ArtScreenDestination
import com.example.artsy.ui.screens.FavouritesScreen.FavouritesScreen
import com.example.artsy.ui.screens.FavouritesScreen.FavouritesScreenDestination
import com.example.artsy.ui.screens.HomeScreen.HomeScreen
import com.example.artsy.ui.screens.HomeScreen.HomeScreenDestination
import com.example.artsy.ui.screens.MainScreen.MainScreen
import com.example.artsy.ui.screens.MainScreen.MainScreenDestination


@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
){

    NavHost(
        navController = navController,
        startDestination = HomeScreenDestination.route,
        modifier = Modifier
    ){

        composable(route = HomeScreenDestination.route) {
            HomeScreen(
            navigateToEntry = {navController.navigate(MainScreenDestination.route)})
        }

        composable(route = MainScreenDestination.route){
            MainScreen(
//                navigateback = {navController.popBackStack()},
                onNavigateUp = { navController.navigateUp() },
                title = MainScreenDestination.title,
                toFavourites = {navController.navigate(FavouritesScreenDestination.route)},
                navController = navController

            )
        }

        composable(route = FavouritesScreenDestination.route){
            FavouritesScreen(
                title = FavouritesScreenDestination.title,
                OnNavigateUp = {navController.navigateUp()}
            )
        }

        composable(route = ArtScreenDestination.routeWithArgs,
                   arguments = listOf(navArgument("itemId"){type = NavType.IntType}))
        {
            val itemId = it.arguments?.getInt("itemId")?: -1
            ArtScreen(itemId, onNavigateUp = {navController.navigateUp()})
        }



    }
}