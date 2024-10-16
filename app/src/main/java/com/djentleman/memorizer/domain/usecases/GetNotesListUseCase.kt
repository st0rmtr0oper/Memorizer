package com.djentleman.memorizer.domain.usecases

import androidx.lifecycle.LiveData
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class GetNotesListUseCase(private val memorizerRepository: MemorizerRepository) {

    fun getNotesList(): LiveData<List<Note>> {
        return memorizerRepository.getNotesList()
    }
}