package com.elewa.marvelapp.modules.characters.character_list.di

import androidx.paging.ExperimentalPagingApi
import com.elewa.marvelapp.modules.characters.character_list.date.repository.CharacterRepositoryImpl
import com.elewa.marvelapp.modules.characters.character_list.domain.repository.CharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@ExperimentalPagingApi
@InstallIn(ViewModelComponent::class)
@Module
abstract class CharacterModule {
    @Binds
    @ViewModelScoped
    abstract fun bindImagesRepository(impl: CharacterRepositoryImpl): CharacterRepository
}