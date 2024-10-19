package com.djentleman.memorizer.domain.usecases

import com.djentleman.memorizer.domain.repository.MemorizerRepository

class DeleteNoteUseCase(private val memorizerRepository: MemorizerRepository) {

    suspend fun deleteNote(id: Int) {
        memorizerRepository.deleteNote(id)
    }
}