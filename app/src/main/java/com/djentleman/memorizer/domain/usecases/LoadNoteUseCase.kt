package com.djentleman.memorizer.domain.usecases

import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class LoadNoteUseCase(private val memorizerRepository: MemorizerRepository) {

    suspend fun loadNote(id: Int): Note {
        return memorizerRepository.loadNote(id)
    }
}