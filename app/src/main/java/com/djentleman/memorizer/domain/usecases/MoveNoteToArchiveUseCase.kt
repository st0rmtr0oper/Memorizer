package com.djentleman.memorizer.domain.usecases

import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class MoveNoteToArchiveUseCase(private val memorizerRepository: MemorizerRepository) {

    suspend fun moveNoteToArchive(id: Int) {
        memorizerRepository.moveNoteToArchive(id)
    }
}