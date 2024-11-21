package com.elewa.marvelapp.modules.characters.character_list.domain.interactor

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import com.elewa.marvelapp.base.BaseUseCase
import com.elewa.marvelapp.modules.characters.character_list.date.dto.CharacterDto
import com.elewa.marvelapp.modules.characters.character_list.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@ExperimentalPagingApi
class GetCharactersUseCase
@Inject constructor(private val repository: CharacterRepository) :
    BaseUseCase<Nothing, Flow<PagingData<CharacterDto>>> {

    override suspend fun execute(params: Nothing?): Flow<PagingData<CharacterDto>> {
        return repository.getAllCharacters()
    }
}