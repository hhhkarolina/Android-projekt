package com.example.projekt


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController




@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantApp(
    onClick: () -> Unit
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: NavScreen.Home.route

    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
        topBar = {
            TopBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(route = NavScreen.AddPlant.route) }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "add")
            }
        }
    ) { padding ->
        NavGraph(navController = navController, modifier = Modifier.padding(padding), onClick = onClick)
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(NavScreen.Home, NavScreen.Notifications, NavScreen.Settings)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        screens.forEach { screen ->
            AddItem(screen = screen, currentDestination = currentDestination, navController = navController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: NavScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    NavigationBarItem(
        label = { Text(text = stringResource(id = screen.title)) },
        icon = { Icon(imageVector = screen.icon, contentDescription = null) },
        selected = currentDestination?.hierarchy?.any {it.route == screen.route} == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(currentScreen) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        }
    )
}

