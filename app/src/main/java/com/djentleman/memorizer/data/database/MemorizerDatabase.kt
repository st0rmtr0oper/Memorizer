package com.djentleman.memorizer.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.Executors

@Database(entities = [NoteDbModel::class], version = 1, exportSchema = false)
abstract class MemorizerDatabase : RoomDatabase() {

    abstract fun memorizerDao(): MemorizerDao

    companion object {

        private var INSTANCE: MemorizerDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "memorizer.db"

        fun getInstance(application: Application): MemorizerDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    MemorizerDatabase::class.java,
                    DB_NAME
                )
                    .setQueryCallback(RoomDatabase.QueryCallback { sqlQuery, bindArgs ->
                        println("SQL Query: $sqlQuery SQL Args: $bindArgs")
                    }, Executors.newSingleThreadExecutor())


                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}