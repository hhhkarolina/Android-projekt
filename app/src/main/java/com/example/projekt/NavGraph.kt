package com.example.projekt


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projekt.ui.screens.*

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier, onClick: () -> Unit) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavScreen.Home.route
    ) {
        composable(route = NavScreen.Home.route) {
            HomeScreen(
                onNavigate = {
                    navController.navigate(it.route)
                }
            )
        }
        composable(route = NavScreen.Notifications.route) {
            NotificationScreen()
        }
        composable(route = NavScreen.Settings.route) {
            SettingsScreen()
        }
        composable(
            route = NavScreen.PlantDetails.route + "?IdP={IdP}",
            arguments = listOf(
                navArgument(name = "IdP") {
                    type = NavType.IntType
                }
            )
        ) {
            PlantDetails({ navController.navigate(route = NavScreen.AddNotification.route) })
        }

        composable(route = NavScreen.AddPlant.route) {
            AddPlant(onClickType = { /*TODO*/ }, onClickSpecies = { /*TODO*/ })
        }

        composable(route = NavScreen.AddNotification.route) {
            AddNotificationScreen(onClick)
        }
    }
}