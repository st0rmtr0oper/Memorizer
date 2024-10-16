package com.djentleman.memorizer.domain.usecases

import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class SaveNoteUseCase(private val memorizerRepository: MemorizerRepository) {

    fun saveNote(note: Note) {
        memorizerRepository.saveNote(note)
    }
}