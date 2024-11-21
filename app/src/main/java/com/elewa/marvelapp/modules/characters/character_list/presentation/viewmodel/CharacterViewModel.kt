package com.elewa.marvelapp.modules.characters.character_list.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.elewa.marvelapp.modules.characters.character_list.date.dto.CharacterDto
import com.elewa.marvelapp.modules.characters.character_list.domain.interactor.GetCharactersUseCase
import com.elewa.marvelapp.modules.characters.character_list.presentation.model.CharacterListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
): ViewModel() {

    private val _imagesStateMutableLiveData = MutableStateFlow<CharacterListState>(CharacterListState())
    val imagesStateLiveData: StateFlow<CharacterListState>
        get() = _imagesStateMutableLiveData


    private val _selectedCharacter = MutableStateFlow<CharacterDto?>(null)
    val selectedCharacter: StateFlow<CharacterDto?>
        get() = _selectedCharacter


    init {
        getCharacterList()
    }

    fun getCharacterList(){
        viewModelScope.launch(Dispatchers.IO) {
            _imagesStateMutableLiveData.emit(CharacterListState(isLoading = true))
            try {
                _imagesStateMutableLiveData.emit(CharacterListState(data =getCharactersUseCase.execute(null).cachedIn(viewModelScope)))
            } catch (e: IOException) {
                _imagesStateMutableLiveData.emit(CharacterListState(error = e))
            } catch (e: Exception) {
                _imagesStateMutableLiveData.emit(CharacterListState(error = e))
            }

        }
    }

    fun onCharacterSelect(character: CharacterDto) {
        viewModelScope.launch(Dispatchers.IO) {
            _selectedCharacter.emit(character)
        }
    }

}