package com.djentleman.memorizer.domain.usecases

import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class MoveNoteToTrashUseCase(private val memorizerRepository: MemorizerRepository) {

    suspend fun moveNoteToTrash(id: Int) {
        return memorizerRepository.moveNoteToTrash(id)
    }
}