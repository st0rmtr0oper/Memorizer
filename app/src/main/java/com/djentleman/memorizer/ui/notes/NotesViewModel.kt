package com.djentleman.memorizer.ui.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.djentleman.memorizer.data.repository.MemorizerRepositoryImpl
import com.djentleman.memorizer.domain.usecases.GetNotesListUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToArchiveUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToTrashUseCase
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    //TODO Это неправильно, pres зависит от data. лечится DI вроде как
    private val repository = MemorizerRepositoryImpl(application)

    private val getNotesListUseCase = GetNotesListUseCase(repository)
    private val moveNoteToArchiveUseCase = MoveNoteToArchiveUseCase(repository)
    private val moveNoteToTrashUseCase = MoveNoteToTrashUseCase(repository)

    val notesList = getNotesListUseCase.getNotesList()

    fun moveNoteToArchive(id: Int) {
        viewModelScope.launch {
            moveNoteToArchiveUseCase.moveNoteToArchive(id)
        }
    }

    fun moveNoteToTrash(id: Int) {
        viewModelScope.launch {
            moveNoteToTrashUseCase.moveNoteToTrash(id)
        }
    }
}