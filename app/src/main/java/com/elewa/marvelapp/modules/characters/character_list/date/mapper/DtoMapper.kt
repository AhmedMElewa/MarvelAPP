package com.elewa.flikerphotos.modules.images.data.mapper


import com.elewa.marvelapp.modules.characters.character_list.date.dto.CharacterDto
import com.elewa.marvelapp.modules.characters.character_list.date.model.CharacterModel


//fun CharacterDto.toEntity() = ImageEntity(
//    id,
//    previewUrl,
//)

fun CharacterModel.toDto() = CharacterDto(
    id = id,
    title = name,
    description = description,
    previewUrl = thumbnail.path,
    previewExtension = thumbnail.extension
)

fun List<CharacterModel>.toDto() = map { it.toDto() }