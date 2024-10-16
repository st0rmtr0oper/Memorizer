package com.djentleman.memorizer.domain.usecases

import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class EditNoteUseCase(private val memorizerRepository: MemorizerRepository) {

    fun editNote(note: Note) {
        memorizerRepository.editNote(note)
    }
}