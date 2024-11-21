package com.elewa.marvelapp.modules.characters.character_list.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import com.elewa.marvelapp.core.extensions.parseError
import com.elewa.marvelapp.core.ui.components.AppTopAppBar
import com.elewa.marvelapp.core.ui.components.LoadingAnimation
import com.elewa.marvelapp.core.ui.navigation.Screen
import com.elewa.marvelapp.modules.characters.character_list.presentation.viewmodel.CharacterViewModel
import com.elewa.marvelapp.core.ui.components.ListCharacter

@OptIn(ExperimentalPagingApi::class)
@Composable
fun CharacterListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CharacterViewModel,
) {


    val characterListState = viewModel.imagesStateLiveData.collectAsState()
    val characterList = characterListState.value.data?.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            AppTopAppBar(
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search Icon"
                        )
                    }
                }
            )
        },
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            if (characterList?.itemSnapshotList?.isNotEmpty() == true) {
                ListCharacter(
                    modifier = modifier,
                    items = characterList,
                    onComicClick = {
                        viewModel.onCharacterSelect(it)
                        navController.navigate(
                            route = Screen.CharacterDetails.route
                        )
                    },
                )
            }
            else  {
                LoadingAnimation(circleSize = 15.dp, spaceBetween = 5.dp)
            }

            if (characterListState.value.error != null) {
                Text(text = characterListState.value.error?.parseError()?.message ?: "Error")
            }
        }

    }

}

