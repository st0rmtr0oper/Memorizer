package com.djentleman.memorizer.domain.usecases

import androidx.lifecycle.LiveData
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class LoadDraftUseCase(private val memorizerRepository: MemorizerRepository) {

    fun loadDraft(): LiveData<Note> {
        return memorizerRepository.loadDraft()
    }
}