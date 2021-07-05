package com.example.instagramuicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.instagramuicompose.home.HomeScreen
import com.example.instagramuicompose.reels.ReelsScreen
import com.example.instagramuicompose.search.SearchScreen
import com.example.instagramuicompose.shop.ShopScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavigator()
        }
    }

    @ExperimentalFoundationApi
    @Composable
    fun AppNavigator() {
        val items = listOf(
            Screen.Home,
            Screen.Search,
            Screen.Reels,
            Screen.Shop,
            Screen.Profile,
        )

        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    backgroundColor = colorResource(id = R.color.white),
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    items.forEach { screen ->
                        BottomNavigationItem(
                            //alwaysShowLabel = false,
                            unselectedContentColor = Color.Black,
                            selectedContentColor = Color.Black,
                            icon = { Icon(screen.icon, "") },
                            label = { Text(stringResource(screen.resourceId)) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    // Pop up to the start destination of the graph to
                                    // avoid building up a large stack of destinations
                                    // on the back stack as users select items
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    // Avoid multiple copies of the same destination when
                                    // reselecting the same item
                                    launchSingleTop = true
                                    // Restore state when reselecting a previously selected item
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            NavHost(navController, startDestination = Screen.Profile.route, Modifier.padding(innerPadding)) {
                composable(Screen.Home.route){ HomeScreen(navController) }
                composable(Screen.Search.route){ SearchScreen(navController) }
                composable(Screen.Reels.route){ ReelsScreen(navController) }
                composable(Screen.Shop.route){ ShopScreen(navController) }
                composable(Screen.Profile.route){ ProfileScreen(navController) }
            }
        }
    }
}