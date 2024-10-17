package com.djentleman.memorizer.domain.usecases

import androidx.lifecycle.LiveData
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class GetTrashListUseCase(private val memorizerRepository: MemorizerRepository) {

    fun getTrashList(): LiveData<List<Note>> {
        return memorizerRepository.getTrashList()
    }
}