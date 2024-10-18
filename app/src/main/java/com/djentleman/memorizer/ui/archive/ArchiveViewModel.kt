package com.djentleman.memorizer.ui.archive

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.djentleman.memorizer.data.repository.MemorizerRepositoryImpl
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.usecases.GetArchivedListUseCase
import com.djentleman.memorizer.domain.usecases.LoadNoteUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToActualUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToTrashUseCase

class ArchiveViewModel(application: Application) : AndroidViewModel(application) {
    //TODO Это неправильно, pres зависит от data
    private val repository = MemorizerRepositoryImpl(application)

    private val getArchivedListUseCase = GetArchivedListUseCase(repository)
    private val loadNoteUseCase = LoadNoteUseCase(repository)
    private val moveNoteToActualUseCase = MoveNoteToActualUseCase(repository)
    private val moveNoteToTrashUseCase = MoveNoteToTrashUseCase(repository)

    val notesList = MutableLiveData<List<Note>>()
    val inspectedNote = MutableLiveData<Note>()
}