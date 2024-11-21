package com.elewa.marvelapp.core.di

import android.content.Context
import androidx.room.Room
import com.elewa.marvelapp.core.data.source.local.CharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Volatile
    private var instance: CharacterDatabase? = null
    private const val DATABASE_NAME = "Marvel-Charactes"

    @Singleton
    @Provides
    fun provideDatabaseInstance(@ApplicationContext context: Context): CharacterDatabase {
        return instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }
    }

    private fun buildDatabase(context: Context): CharacterDatabase {
        return Room.databaseBuilder(context, CharacterDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()
    }
}