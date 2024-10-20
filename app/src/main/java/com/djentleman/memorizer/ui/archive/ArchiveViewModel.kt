package com.djentleman.memorizer.ui.archive

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.djentleman.memorizer.data.repository.MemorizerRepositoryImpl
import com.djentleman.memorizer.domain.usecases.GetArchivedListUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToActualUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToArchiveUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToTrashUseCase
import kotlinx.coroutines.launch

class ArchiveViewModel(application: Application) : AndroidViewModel(application) {
    //TODO Это неправильно, pres зависит от data
    private val repository = MemorizerRepositoryImpl(application)

    private val getArchivedListUseCase = GetArchivedListUseCase(repository)
    private val moveNoteToActualUseCase = MoveNoteToActualUseCase(repository)
    private val moveNoteToTrashUseCase = MoveNoteToTrashUseCase(repository)
    private val moveNoteToArchiveUseCase = MoveNoteToArchiveUseCase(repository)

    val notesList = getArchivedListUseCase.getArchivedList()

    fun moveNoteToTrash(id: Int) {
        viewModelScope.launch {
            moveNoteToTrashUseCase.moveNoteToTrash(id)
        }
    }

    fun moveNoteToActual(id: Int) {
        viewModelScope.launch {
            moveNoteToActualUseCase.moveNoteToActual(id)
        }
    }

    fun moveNoteToArchive(id: Int) {
        viewModelScope.launch {
            moveNoteToArchiveUseCase.moveNoteToArchive(id)
        }
    }
}