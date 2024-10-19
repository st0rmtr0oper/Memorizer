package com.djentleman.memorizer.domain.usecases

import com.djentleman.memorizer.domain.repository.MemorizerRepository

class ClearAllTrashUseCase(private val memorizerRepository: MemorizerRepository) {

    suspend fun clearAllTrash() {
        memorizerRepository.clearAllTrash()
    }
}