package com.elewa.marvelapp.modules.characters.character_list.date.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.elewa.flikerphotos.modules.images.data.mapper.toDto
import com.elewa.marvelapp.core.data.constants.Constants
import com.elewa.marvelapp.core.data.constants.getOffsetByPage
import com.elewa.marvelapp.core.data.source.local.CharacterDatabase
import com.elewa.marvelapp.modules.characters.character_list.date.dto.CharacterDto
import com.elewa.marvelapp.modules.characters.character_list.date.dto.RemoteKeyDto
import com.elewa.marvelapp.modules.characters.character_list.date.source.remote.CharactersApiInterface

@ExperimentalPagingApi
class CharactersRemoteMediator(
    private val apiInterface: CharactersApiInterface,
    private val characterDatabase: CharacterDatabase
) : RemoteMediator<Int, CharacterDto>() {

    private val characterDao = characterDatabase.characterDao()
    private val remoteKeyDao = characterDatabase.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterDto> // All data stored in db
    ): MediatorResult {
        return try {
            Log.d("MEDIATOR", "loadType $loadType")
            val currentPage = when (loadType) {

                /*Generally, this means that a request to load remote data and replace all local data was made.*/
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    //Here there will always be an item, because it was already saved when in REFRESH.
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            /*if false no data is saved in the local database, requests data from the network.
                            * if true has reached the end of the page, stops placing items at the beginning and
                            * goes to the next loadType.
                            * */
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    //Here there will always be an item, because it was already saved when in REFRESH.
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    Log.d("MEDIATOR", "loadType $remoteKeys")

                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = apiInterface.getCharacters(
                offset = getOffsetByPage(currentPage),
                limit = Constants.PAGE_ITEMS_COUNT
            )

            val characterResponse = response.data!!.results
            val endOfPaginationReached = characterResponse!!.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            characterDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    //initial load, clear all data from db
                    characterDao.deleteAllCharacters()
                    remoteKeyDao.deleteAllRemoteKeys()
                }
                val keys = characterResponse.map { character ->
                    RemoteKeyDto(
                        id = character.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }

                val mappedData = characterResponse.toDto()
                remoteKeyDao.insertAll(keys)
                characterDao.insertAll(mappedData)

            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharacterDto>
    ): RemoteKeyDto? {

        return state.anchorPosition?.let { position ->
            Log.d("MEDIATOR", "position $position")
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeyDao.getRemoteKey(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, CharacterDto>
    ): RemoteKeyDto? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { comic ->
                remoteKeyDao.getRemoteKey(id = comic.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, CharacterDto>
    ): RemoteKeyDto? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { comic ->
                remoteKeyDao.getRemoteKey(id = comic.id)
            }
    }

}