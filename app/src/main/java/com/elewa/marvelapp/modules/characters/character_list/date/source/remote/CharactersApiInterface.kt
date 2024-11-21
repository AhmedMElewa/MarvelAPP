package com.elewa.marvelapp.modules.characters.character_list.date.source.remote

import com.elewa.marvelapp.BuildConfig
import com.elewa.marvelapp.core.data.constants.Constants
import com.elewa.marvelapp.modules.characters.character_list.date.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApiInterface {

    @GET("characters")
    suspend fun getCharacters(
        @Query("ts") ts: String = Constants.ts,
        @Query("apikey") apiKey: String = BuildConfig.API_KEY,
        @Query("hash") hash: String = Constants.generateMd5Hash(),
        @Query("limit") limit: Int = Constants.PAGE_ITEMS_COUNT,
        @Query("offset") offset: Int = 0,
    ) : CharacterResponse

    @GET("characters")
    suspend fun searchCharacters(
        @Query("ts") ts: String = Constants.ts,
        @Query("apikey") apiKey: String = BuildConfig.API_KEY,
        @Query("hash") hash: String = Constants.generateMd5Hash(),
        @Query("limit") limit: Int = Constants.PAGE_ITEMS_COUNT,
        @Query("nameStartsWith") query: String,
        @Query("offset") offset: Int = 0,
    ) : CharacterResponse
}