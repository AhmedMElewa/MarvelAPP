package com.elewa.marvelapp.modules.characters.character_list.presentation.model

import androidx.paging.PagingData
import com.elewa.marvelapp.modules.characters.character_list.date.dto.CharacterDto
import kotlinx.coroutines.flow.Flow


data class CharacterListState(
    val isLoading: Boolean = false,
    val data: Flow<PagingData<CharacterDto>>? = null,
    val error: Throwable? = null
)