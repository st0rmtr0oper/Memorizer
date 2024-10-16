package com.djentleman.memorizer.domain.usecases

import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class RedactNoteUsecase(private val memorizerRepository: MemorizerRepository) {

    fun redactNote(note: Note) {
        memorizerRepository.redactNote(note)
    }
}