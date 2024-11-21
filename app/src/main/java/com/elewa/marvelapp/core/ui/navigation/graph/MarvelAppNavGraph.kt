package com.elewa.marvelapp.core.ui.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.paging.ExperimentalPagingApi
import com.elewa.marvelapp.core.ui.navigation.MARVEL_APP_GRAPH_ROUTE
import com.elewa.marvelapp.core.ui.navigation.Screen
import com.elewa.marvelapp.modules.characters.character_details.presentation.ui.CharacterDetailsScreen
import com.elewa.marvelapp.modules.characters.character_list.presentation.ui.CharacterListScreen
import com.elewa.marvelapp.modules.characters.character_list.presentation.viewmodel.CharacterViewModel

@OptIn(ExperimentalPagingApi::class)
fun NavGraphBuilder.marvelAppNav(navController: NavController) {
    navigation(
        startDestination = Screen.CharacterList.route,
        route = MARVEL_APP_GRAPH_ROUTE
    ) {
        composable(
            route = Screen.CharacterList.route
        ) {
            val viewModel = it.sharedViewModel<CharacterViewModel>(navController)
            CharacterListScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            route = Screen.CharacterDetails.route
        ) {
            val viewModel = it.sharedViewModel<CharacterViewModel>(navController)
            CharacterDetailsScreen(navController = navController, viewModel = viewModel)

        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return hiltViewModel(parentEntry)
}