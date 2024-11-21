package com.elewa.marvelapp.modules.characters.character_list.date.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elewa.marvelapp.core.data.source.local.IMAGE_TABLE

@Entity(tableName = IMAGE_TABLE)
data class CharacterDto(
    @PrimaryKey
    val id: String,
    @ColumnInfo
    val title: String?,
    @ColumnInfo
    val description: String?,
    @ColumnInfo(name = "path")
    val previewUrl: String?,
    @ColumnInfo(name = "extension")
    val previewExtension: String?,
)