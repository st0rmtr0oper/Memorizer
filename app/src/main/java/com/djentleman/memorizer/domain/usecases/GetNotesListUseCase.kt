package com.djentleman.memorizer.domain.usecases

import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class GetNotesListUseCase(private val memorizerRepository: MemorizerRepository) {

    fun getNotesList(): List<Note> {
        return memorizerRepository.getNotesList()
    }
}