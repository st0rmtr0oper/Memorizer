package com.djentleman.memorizer.ui.trash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.djentleman.memorizer.data.repository.MemorizerRepositoryImpl
import com.djentleman.memorizer.domain.usecases.ClearAllTrashUseCase
import com.djentleman.memorizer.domain.usecases.DeleteNoteUseCase
import com.djentleman.memorizer.domain.usecases.GetTrashListUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToActualUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToArchiveUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToTrashUseCase
import kotlinx.coroutines.launch

class TrashViewModel(application: Application) : AndroidViewModel(application) {
    //TODO Это неправильно, pres зависит от data
    private val repository = MemorizerRepositoryImpl(application)

    private val getTrashListUseCase = GetTrashListUseCase(repository)
    private val moveNoteToActualUseCase = MoveNoteToActualUseCase(repository)
    private val moveNoteToArchiveUseCase = MoveNoteToArchiveUseCase(repository)
    private val moveNoteToTrashUseCase = MoveNoteToTrashUseCase(repository)
    private val deleteNoteUseCase = DeleteNoteUseCase(repository)
    private val clearAllTrashUseCase = ClearAllTrashUseCase(repository)

    val notesList = getTrashListUseCase.getTrashList()

    fun moveNoteToArchive(id: Int) {
        viewModelScope.launch {
            moveNoteToArchiveUseCase.moveNoteToArchive(id)
        }
    }

    fun moveNoteToActual(id: Int) {
        viewModelScope.launch {
            moveNoteToActualUseCase.moveNoteToActual(id)
        }
    }

    fun moveNoteToTrash(id: Int) {
        viewModelScope.launch {
            moveNoteToTrashUseCase.moveNoteToTrash(id)
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            deleteNoteUseCase.deleteNote(id)
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            clearAllTrashUseCase.clearAllTrash()
        }
    }
}