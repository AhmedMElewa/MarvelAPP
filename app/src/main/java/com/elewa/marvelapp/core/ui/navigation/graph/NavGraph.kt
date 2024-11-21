package com.elewa.marvelapp.core.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.elewa.marvelapp.core.ui.navigation.MARVEL_APP_GRAPH_ROUTE
import com.elewa.marvelapp.core.ui.navigation.ROOT_GRAPH_ROUTE

@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = MARVEL_APP_GRAPH_ROUTE,
        route = ROOT_GRAPH_ROUTE
    ) {
        marvelAppNav(navController)
    }
}