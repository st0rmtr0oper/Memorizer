package com.djentleman.memorizer.ui.archive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.djentleman.memorizer.domain.usecases.GetArchivedListUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToActualUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToArchiveUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToTrashUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class ArchiveViewModel @Inject constructor(
    private val getArchivedListUseCase: GetArchivedListUseCase,
    private val moveNoteToActualUseCase: MoveNoteToActualUseCase,
    private val moveNoteToTrashUseCase: MoveNoteToTrashUseCase,
    private val moveNoteToArchiveUseCase: MoveNoteToArchiveUseCase
) : ViewModel() {

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