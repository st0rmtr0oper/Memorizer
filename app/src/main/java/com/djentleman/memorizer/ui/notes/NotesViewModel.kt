package com.djentleman.memorizer.ui.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.djentleman.memorizer.data.repository.MemorizerRepositoryImpl
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.usecases.AddNoteUseCase
import com.djentleman.memorizer.domain.usecases.GetNotesListUseCase
import com.djentleman.memorizer.domain.usecases.LoadNoteUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToArchiveUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToTrashUseCase
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    //TODO Это неправильно, pres зависит от data. лечится DI вроде как
    private val repository = MemorizerRepositoryImpl(application)

    private val getNotesListUseCase = GetNotesListUseCase(repository)
    private val addNoteListUseCase = AddNoteUseCase(repository)
    private val loadNoteUseCase = LoadNoteUseCase(repository)
    private val moveNoteToArchiveUseCase = MoveNoteToArchiveUseCase(repository)
    private val moveNoteToTrashUseCase = MoveNoteToTrashUseCase(repository)

    val notesList = getNotesListUseCase.getNotesList()

//    suspend fun getNotesList() {
//        notesList.value = getNotesListUseCase.getNotesList()
//    }

     fun addNote(note: Note) {
        viewModelScope.launch {
            addNoteListUseCase.addNote(note)
        }

    }

//    suspend fun inspectNote(id: Int) {
//        inspectedNote.value = inspectNoteUseCase.inspectNote(id)
//    }

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

//    fun changeEnableState(shopItem: ShopItem) {
//        val newItem = shopItem.copy(enabled = !shopItem.enabled)
//    editShopItemUsecase.editshopItem(newItem)
//    getShopList()
//    }

//    setValue - main thread
//    postValue - any thread

//    нужен контекст - наслед от AndroidViewModel + application
//    не нужен - то от ViewModel

    //есть способ с автообновлением через применение LiveData в юзкейсах и репах,
    //но я пока не понял зачем себе усложнять жизнь
}