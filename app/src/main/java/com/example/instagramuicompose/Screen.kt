package com.example.instagramuicompose

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    object Home : Screen("home", R.string.home, Icons.Default.Home)
    object Search : Screen("search", R.string.search, Icons.Default.Search)
    object Reels : Screen("reels", R.string.reels, Icons.Default.VideoCall)
    object Shop : Screen("shop", R.string.shop, Icons.Default.Shop)
    object Profile : Screen("profileScreen", R.string.profile, Icons.Default.Person)
}