package com.example.instagramuicompose.ui.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.instagramuicompose.R

@ExperimentalFoundationApi
@Composable
fun SearchScreen (navController: NavController){
    Text(stringResource(R.string.search))
}