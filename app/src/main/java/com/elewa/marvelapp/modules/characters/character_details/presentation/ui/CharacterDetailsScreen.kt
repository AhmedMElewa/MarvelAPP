package com.elewa.marvelapp.modules.characters.character_details.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.ExperimentalPagingApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.elewa.marvelapp.R
import com.elewa.marvelapp.core.ui.components.AppTopAppBar
import com.elewa.marvelapp.modules.characters.character_list.presentation.viewmodel.CharacterViewModel

@OptIn(ExperimentalPagingApi::class)
@Composable
fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: CharacterViewModel,
) {

    val character by viewModel.selectedCharacter.collectAsState(null)

    Scaffold(
        topBar = {
            AppTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Arrow Back Icon"
                        )
                    }
                },
            )
        },
    ) { paddingValues ->
        Column(Modifier.padding(paddingValues)) {
            LazyColumn(Modifier.weight(1f)) {
                item {
                    AsyncImage(
                        model =
                        ImageRequest.Builder(LocalContext.current)
                            .data("${character?.previewUrl}.${character?.previewExtension}")
                            .crossfade(true)
                            .placeholder(R.drawable.img_placeholder)
                            .build(),
                        contentDescription = character?.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop

                    )
                    Text(
                        text = character?.title?:"",
                        style = TextStyle(
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold

                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                    )
                }

                character?.description?.let {
                    item {
                        Text(
                            modifier = Modifier.padding(12.dp),
                            text = character?.description?:"",
                            color = MaterialTheme.colorScheme.onSurface,
                        )

                    }
                }
            }
        }
    }
}