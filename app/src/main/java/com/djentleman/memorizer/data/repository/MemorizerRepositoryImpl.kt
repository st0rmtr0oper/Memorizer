package com.djentleman.memorizer.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.djentleman.memorizer.data.database.MemorizerDatabase
import com.djentleman.memorizer.data.database.MemorizerMapper
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class MemorizerRepositoryImpl(application: Application): MemorizerRepository {

    private val memorizerDao = MemorizerDatabase.getInstance(application).memorizerDao()
    private val mapper = MemorizerMapper()

    override suspend fun addNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun getNotesList(): LiveData<List<Note>> = memorizerDao.getNotesList().map{
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun getArchivedList(): LiveData<List<Note>> = memorizerDao.getArchivedList().map{
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun getTrashList(): LiveData<List<Note>> = memorizerDao.getTrashList().map{
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun inspectNote(id: Int): Note {
        TODO("Not yet implemented")
    }

    override suspend fun editNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend    fun saveNote(note: Note) {
        TODO("Not yet implemented")
    }
}