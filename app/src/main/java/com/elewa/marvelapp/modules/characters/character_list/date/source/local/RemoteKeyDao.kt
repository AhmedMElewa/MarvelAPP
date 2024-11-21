package com.elewa.marvelapp.modules.characters.character_list.date.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.elewa.marvelapp.modules.characters.character_list.date.dto.RemoteKeyDto

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(remoteKeyDto: RemoteKeyDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeyDto: List<RemoteKeyDto>)

    @Query("SELECT * FROM remote_key ORDER BY id DESC")
    suspend fun getRemoteKey(): List<RemoteKeyDto>

    @Query("SELECT * FROM remote_key WHERE id =:id")
    suspend fun getRemoteKey(id: String): RemoteKeyDto

    @Query("DELETE FROM remote_key")
    suspend fun deleteAllRemoteKeys()
}