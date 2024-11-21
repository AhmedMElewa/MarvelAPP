package com.elewa.marvelapp.modules.characters.character_list.di

import com.elewa.marvelapp.modules.characters.character_list.date.source.remote.CharactersApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object CharacterModuleProvider {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): CharactersApiInterface {
        return retrofit.create(CharactersApiInterface::class.java)
    }
}