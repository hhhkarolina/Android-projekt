package com.example.projekt

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector


sealed class NavScreen(
    @StringRes val title: Int,
    val route: String,
    val icon: ImageVector = Icons.Filled.Favorite
) {
    object Home: NavScreen(title = R.string.home, route = "home", icon = Icons.Filled.Home)
    object Notifications: NavScreen(title = R.string.notifications, route = "notifications", icon = Icons.Filled.Notifications)
    object Settings: NavScreen(title = R.string.settings, route = "settings", icon = Icons.Filled.Settings)
    object PlantDetails: NavScreen(title = R.string.plant_details, route = "plant_details")
    object AddPlant: NavScreen(title = R.string.add_plant, route = "add_plant")
    object AddNotification: NavScreen(title = R.string.add_notification, route = "add_notification")
}