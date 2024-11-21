package com.elewa.marvelapp.modules.characters.character_list.date.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.elewa.marvelapp.core.data.constants.Constants
import com.elewa.marvelapp.core.data.source.local.CharacterDatabase
import com.elewa.marvelapp.modules.characters.character_list.date.dto.CharacterDto
import com.elewa.marvelapp.modules.characters.character_list.date.paging.CharactersRemoteMediator
import com.elewa.marvelapp.modules.characters.character_list.date.source.remote.CharactersApiInterface
import com.elewa.marvelapp.modules.characters.character_list.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class CharacterRepositoryImpl @Inject constructor(
    private val apiInterface: CharactersApiInterface,
    private val characterDatabase: CharacterDatabase
) : CharacterRepository {

    override fun getAllCharacters(): Flow<PagingData<CharacterDto>> {
        val pagingSourceFactory = { characterDatabase.characterDao().getAllCharacters() }
        return Pager(
            config = PagingConfig(pageSize = Constants.PAGE_ITEMS_COUNT),
            remoteMediator = CharactersRemoteMediator(
                apiInterface = apiInterface,
                characterDatabase = characterDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow

    }

}