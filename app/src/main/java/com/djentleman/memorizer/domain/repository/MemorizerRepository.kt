package com.djentleman.memorizer.domain.repository

import com.djentleman.memorizer.domain.models.Note

interface MemorizerRepository {

    fun addNote(note: Note)
    fun deleteNote(note: Note)
    fun getNotesList(): List<Note>
    fun inspectNote(id: Int): Note
    fun redactNote(note: Note)
    fun saveNote(note: Note)
}