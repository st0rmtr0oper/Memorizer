package com.djentleman.memorizer.domain.repository

import androidx.lifecycle.LiveData
import com.djentleman.memorizer.domain.models.Note

interface MemorizerRepository {
    fun getNotesList(): LiveData<List<Note>>
    fun getArchivedList(): LiveData<List<Note>>
    fun getTrashList(): LiveData<List<Note>>
    suspend fun addNote(note: Note)
    suspend fun deleteNote(id: Int)
    fun loadNote(id: Int): LiveData<Note>
    suspend fun saveNote(note: Note)
    suspend fun moveNoteToActual(id: Int)
    suspend fun moveNoteToArchive(id: Int)
    suspend fun moveNoteToTrash(id: Int)
    suspend fun clearAllTrash()
    fun saveDraft(note: Note)
    fun loadDraft(): LiveData<Note>
}