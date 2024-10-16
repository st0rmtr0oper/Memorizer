package com.djentleman.memorizer.domain.usecases

import androidx.lifecycle.LiveData
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class GetArchivedListUseCase(private val memorizerRepository: MemorizerRepository) {

    suspend fun getArchivedList(): LiveData<List<Note>> {
        return memorizerRepository.getArchivedList()
    }
}