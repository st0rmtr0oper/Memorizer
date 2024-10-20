package com.djentleman.memorizer.ui.editor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.djentleman.memorizer.data.repository.MemorizerRepositoryImpl
import com.djentleman.memorizer.domain.models.EditorMode
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.usecases.AddNoteUseCase
import com.djentleman.memorizer.domain.usecases.LoadDraftUseCase
import com.djentleman.memorizer.domain.usecases.LoadNoteUseCase
import com.djentleman.memorizer.domain.usecases.SaveDraftUseCase
import com.djentleman.memorizer.domain.usecases.SaveNoteUseCase
import kotlinx.coroutines.launch

class EditorViewModel(application: Application, mode: EditorMode, noteId: Int) :
    AndroidViewModel(application) {
    //TODO Это неправильно, pres зависит от data. лечится DI вроде как
    private val repository = MemorizerRepositoryImpl(application)

    private val loadNoteUseCase = LoadNoteUseCase(repository)
    private val loadDraftUseCase = LoadDraftUseCase(repository)
    private val saveNoteUseCase = SaveNoteUseCase(repository)
    private val saveDraftUseCase = SaveDraftUseCase(repository)
    private val addNoteUseCase = AddNoteUseCase(repository)

    var note = load(mode, noteId)

    private fun load(mode: EditorMode, id: Int): LiveData<Note> {
        return when (mode) {
            EditorMode.ADD -> loadDraft()
            EditorMode.EDIT -> loadNote(id)
            EditorMode.INSPECT -> loadNote(id)
        }
    }

    fun loadNote(id: Int): LiveData<Note> {
        return loadNoteUseCase.loadNote(id)
        //TODO скоуп нужен?
    }

    fun saveNote(note: Note) {
        viewModelScope.launch {
            saveNoteUseCase.saveNote(note)
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            addNoteUseCase.addNote(note)
        }
    }

    fun loadDraft(): LiveData<Note> {
        return loadDraftUseCase.loadDraft()
    }

    fun saveDraft(note: Note) {
        saveDraftUseCase.saveDraft(note)
    }
}