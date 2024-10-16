package com.djentleman.memorizer.domain.repository

import androidx.lifecycle.LiveData
import com.djentleman.memorizer.domain.models.Note

interface MemorizerRepository {

    suspend fun addNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun getNotesList(): LiveData<List<Note>>
    suspend fun getArchivedList(): LiveData<List<Note>>
    suspend fun getTrashList(): LiveData<List<Note>>
    suspend fun inspectNote(id: Int): Note
    suspend fun editNote(note: Note)
    suspend fun saveNote(note: Note)
}