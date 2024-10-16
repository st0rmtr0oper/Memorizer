package com.djentleman.memorizer.domain.usecases

import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class MoveNoteToActualUseCase(private val memorizerRepository: MemorizerRepository) {

    suspend fun moveNoteToActual(id: Int) {
        return memorizerRepository.moveNoteToActual(id)
    }
}