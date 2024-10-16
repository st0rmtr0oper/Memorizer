package com.djentleman.memorizer.domain.usecases

import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class InspectNoteUsecase(private val memorizerRepository: MemorizerRepository) {

    fun inspectNote(id: Int): Note {
        return memorizerRepository.inspectNote(id)
    }
}