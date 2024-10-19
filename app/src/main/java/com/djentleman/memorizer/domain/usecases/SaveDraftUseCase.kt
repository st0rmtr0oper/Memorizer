package com.djentleman.memorizer.domain.usecases

import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class SaveDraftUseCase (private val memorizerRepository: MemorizerRepository) {

    suspend fun saveDraft(note: Note) {
        memorizerRepository.saveDraft(note)
    }
}