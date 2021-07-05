package com.example.instagramuicompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        val navController = rememberNavController()
        
        NavHost(
            navController = navController,
            startDestination = "profileScreen"
        ) {
            composable("profileScreen"){ ProfileScreen()}
        }
    }
}