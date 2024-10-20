package com.djentleman.memorizer.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.djentleman.memorizer.data.database.MemorizerDao
import com.djentleman.memorizer.data.database.MemorizerDatabase
import com.djentleman.memorizer.data.repository.MemorizerRepositoryImpl
import com.djentleman.memorizer.domain.repository.MemorizerRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindMemorizerRepository(impl: MemorizerRepositoryImpl): MemorizerRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideMemorizerDao(
            application: Application
        ): MemorizerDao {
            return MemorizerDatabase.getInstance(application).memorizerDao()
        }

        @ApplicationScope
        @Provides
        fun provideSharedPreferences(application: Application): SharedPreferences {
            return application.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        }
    }
}
