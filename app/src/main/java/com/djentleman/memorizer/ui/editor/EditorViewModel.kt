package com.djentleman.memorizer.ui.editor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.djentleman.memorizer.data.repository.MemorizerRepositoryImpl
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.usecases.LoadDraftUseCase
import com.djentleman.memorizer.domain.usecases.LoadNoteUseCase
import com.djentleman.memorizer.domain.usecases.SaveDraftUseCase
import com.djentleman.memorizer.domain.usecases.SaveNoteUseCase
import kotlinx.coroutines.launch

class EditorViewModel(application: Application) : AndroidViewModel(application) {
    //TODO Это неправильно, pres зависит от data. лечится DI вроде как
    private val repository = MemorizerRepositoryImpl(application)

    private val loadNoteUseCase = LoadNoteUseCase(repository)
    private val loadDraftUseCase = LoadDraftUseCase(repository)
    private val saveNoteUseCase = SaveNoteUseCase(repository)
    private val saveDraftUseCase = SaveDraftUseCase(repository)

    val note = MutableLiveData<Note>()

    fun loadNote(id: Int) {
        viewModelScope.launch {
            val newNote = loadNoteUseCase.loadNote(id)
            note.postValue(newNote)
        }
    }

    fun saveNote(note: Note) {
        viewModelScope.launch {
            saveNoteUseCase.saveNote(note)

            //How callbacks work?
            //success dialog -> exit command?
        }
    }

    fun loadDraft() {
        viewModelScope.launch {
            val newNote = loadDraftUseCase.loadDraft()
            note.postValue(newNote)
        }
    }

    fun saveDraft(note: Note) {
        viewModelScope.launch {
            saveDraftUseCase.saveDraft(note)
            //exit callback?
        }
    }
}