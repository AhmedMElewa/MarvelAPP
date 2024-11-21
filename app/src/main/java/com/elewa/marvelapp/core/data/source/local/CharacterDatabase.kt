package com.elewa.marvelapp.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.elewa.marvelapp.modules.characters.character_list.date.dto.CharacterDto
import com.elewa.marvelapp.modules.characters.character_list.date.dto.RemoteKeyDto
import com.elewa.marvelapp.modules.characters.character_list.date.source.local.CharacterDao
import com.elewa.marvelapp.modules.characters.character_list.date.source.local.RemoteKeyDao

@Database(entities = [CharacterDto::class, RemoteKeyDto::class], version = 1, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeyDao(): RemoteKeyDao
}

const val IMAGE_TABLE = "image"
const val REMOTE_KEY_TABLE = "remote_key"