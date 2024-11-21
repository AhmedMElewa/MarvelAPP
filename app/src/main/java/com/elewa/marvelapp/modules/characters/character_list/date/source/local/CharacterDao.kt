package com.elewa.marvelapp.modules.characters.character_list.date.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elewa.marvelapp.modules.characters.character_list.date.dto.CharacterDto

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: List<CharacterDto>): List<Long>

    @Query("SELECT * FROM image")
    fun getAllCharacters(): PagingSource<Int, CharacterDto>

    @Query("DELETE FROM image")
    suspend fun deleteAllCharacters()

}