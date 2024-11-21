package com.elewa.marvelapp.core.ui.navigation

const val ROOT_GRAPH_ROUTE = "root"
const val MARVEL_APP_GRAPH_ROUTE = "CHARACTER_LIST"

sealed class Screen(val route: String) {
    object CharacterList : Screen(route = "character_list")
    object CharacterDetails : Screen(route = "character_details")
}
