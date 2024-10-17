package com.djentleman.memorizer.ui.trash

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.djentleman.memorizer.data.repository.MemorizerRepositoryImpl
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.usecases.ClearAllTrashUseCase
import com.djentleman.memorizer.domain.usecases.DeleteNoteUseCase
import com.djentleman.memorizer.domain.usecases.GetTrashListUseCase
import com.djentleman.memorizer.domain.usecases.InspectNoteUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToActualUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToArchiveUseCase

class TrashViewModel(application: Application ) : AndroidViewModel(application) {
    //TODO Это неправильно, pres зависит от data
    private val repository = MemorizerRepositoryImpl(application)

    private val getTrashListUseCase = GetTrashListUseCase(repository)
    private val inspectNoteUseCase = InspectNoteUseCase(repository)
    private val moveNoteToActualUseCase = MoveNoteToActualUseCase(repository)
    private val moveNoteToArchiveUseCase = MoveNoteToArchiveUseCase(repository)
    private val deleteNoteUseCase = DeleteNoteUseCase(repository)
    private val clearAllTrashUseCase = ClearAllTrashUseCase(repository)

    val notesList = MutableLiveData<List<Note>>()
    val inspectedNote = MutableLiveData<Note>()
}