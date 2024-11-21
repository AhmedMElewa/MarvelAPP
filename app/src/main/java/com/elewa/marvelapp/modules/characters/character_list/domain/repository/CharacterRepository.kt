package com.elewa.marvelapp.modules.characters.character_list.domain.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.elewa.marvelapp.modules.characters.character_list.date.dto.CharacterDto
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
interface CharacterRepository  {

    fun getAllCharacters(): Flow<PagingData<CharacterDto>>
}