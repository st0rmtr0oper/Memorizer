package com.djentleman.memorizer.ui.editor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.djentleman.memorizer.data.repository.MemorizerRepositoryImpl
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.usecases.AddNoteUseCase
import com.djentleman.memorizer.domain.usecases.LoadDraftUseCase
import com.djentleman.memorizer.domain.usecases.LoadNoteUseCase
import com.djentleman.memorizer.domain.usecases.SaveDraftUseCase
import com.djentleman.memorizer.domain.usecases.SaveNoteUseCase
import kotlinx.coroutines.launch

class EditorViewModel(application: Application) :
    AndroidViewModel(application) {
    //TODO Это неправильно, pres зависит от data. лечится DI вроде как
    private val repository = MemorizerRepositoryImpl(application)

    private val loadNoteUseCase = LoadNoteUseCase(repository)
    private val loadDraftUseCase = LoadDraftUseCase(repository)
    private val saveNoteUseCase = SaveNoteUseCase(repository)
    private val saveDraftUseCase = SaveDraftUseCase(repository)
    private val addNoteUseCase = AddNoteUseCase(repository)

    //хз насколько правильно я реализовал подгрузку данных в note, костыли какие то. но я не хотел
    //прокидывать нужные значения (mode, noteId) через констуркторы, это выглядит еще хуже
    val note = MutableLiveData<Note>()

    fun loadNote(id: Int) {
        //TODO скоуп нужен?
//        viewModelScope.launch {
        val log = loadNoteUseCase.loadNote(id).value

        //TODO почему то null получаю тут

    //            note.postValue(
//                loadNoteUseCase.loadNote(id).value
//            )
//        }
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

    fun loadDraft() {
        viewModelScope.launch {
            note.postValue(
                loadDraftUseCase.loadDraft().value
            )
        }
    }

    fun saveDraft(note: Note) {
        viewModelScope.launch {
            saveDraftUseCase.saveDraft(note)
        }
    }
}