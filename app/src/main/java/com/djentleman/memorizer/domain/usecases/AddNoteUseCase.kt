package com.djentleman.memorizer.domain.usecases

import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class AddNoteUseCase(private val memorizerRepository: MemorizerRepository) {

    fun addNote(note: Note) {
        memorizerRepository.addNote(note)
    }
}