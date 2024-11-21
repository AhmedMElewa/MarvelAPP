package com.elewa.marvelapp.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.elewa.marvelapp.core.extensions.items
import com.elewa.marvelapp.modules.characters.character_list.date.dto.CharacterDto


@Composable
fun ListCharacter(
    items: LazyPagingItems<CharacterDto>,
    modifier: Modifier,
    onComicClick: (CharacterDto) -> Unit,
) {
    LazyColumn(modifier) {
        items(
            items = items,
            key = { it.id }) { character ->
            CharacterCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 6.dp
                    ),
                title = character.title,
                description = character.description,
                imageUrl = "${character.previewUrl}.${character.previewExtension}",
                onComicClick = { onComicClick(character) }
            )
        }
    }
}