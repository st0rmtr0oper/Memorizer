package com.djentleman.memorizer.domain.repository

import androidx.lifecycle.LiveData
import com.djentleman.memorizer.domain.models.Note

interface MemorizerRepository {
    fun getNotesList(): LiveData<List<Note>>
    fun getArchivedList(): LiveData<List<Note>>
    fun getTrashList(): LiveData<List<Note>>
    suspend fun addNote(note: Note)
    suspend fun deleteNote(note: Note)
    suspend fun inspectNote(id: Int): Note
    suspend fun editNote(note: Note)
    suspend fun saveNote(note: Note)
    suspend fun moveNoteToActual(id: Int)
    suspend fun moveNoteToArchive(id: Int)
    suspend fun moveNoteToTrash(id: Int)
    suspend fun clearAllTrash()
}