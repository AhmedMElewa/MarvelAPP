package com.elewa.marvelapp.modules.characters.character_list.date.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elewa.marvelapp.core.data.source.local.REMOTE_KEY_TABLE

@Entity(tableName = REMOTE_KEY_TABLE)
data class RemoteKeyDto(
    @PrimaryKey
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?,
)