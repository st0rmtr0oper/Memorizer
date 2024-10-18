package com.djentleman.memorizer.domain.usecases

import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class LoadDraftUseCase (private val memorizerRepository: MemorizerRepository) {

    fun loadDraft(): Note {
        return memorizerRepository.loadDraft()
    }
}