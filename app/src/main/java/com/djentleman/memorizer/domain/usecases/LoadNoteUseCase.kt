package com.djentleman.memorizer.domain.usecases

import androidx.lifecycle.LiveData
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class LoadNoteUseCase(private val memorizerRepository: MemorizerRepository) {

    fun loadNote(id: Int): LiveData<Note> {
        return memorizerRepository.loadNote(id)
    }
}