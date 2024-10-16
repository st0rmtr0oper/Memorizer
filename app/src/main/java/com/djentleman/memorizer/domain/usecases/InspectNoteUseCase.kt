package com.djentleman.memorizer.domain.usecases

import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class InspectNoteUseCase(private val memorizerRepository: MemorizerRepository) {

    suspend fun inspectNote(id: Int): Note {
        return memorizerRepository.inspectNote(id)
    }
}