package com.djentleman.memorizer.ui.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.djentleman.memorizer.data.repository.MemorizerRepositoryImpl
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.usecases.AddNoteUseCase
import com.djentleman.memorizer.domain.usecases.GetNotesListUseCase
import com.djentleman.memorizer.domain.usecases.InspectNoteUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToArchiveUseCase
import com.djentleman.memorizer.domain.usecases.MoveNoteToTrashUseCase

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    //TODO Это неправильно, pres зависит от data
    private val repository = MemorizerRepositoryImpl(application)

    private val getNotesListUseCase = GetNotesListUseCase(repository)
    private val addNoteListUseCase = AddNoteUseCase(repository)
    private val inspectNoteUseCase = InspectNoteUseCase(repository)
    private val moveNoteToArchiveUseCase = MoveNoteToArchiveUseCase(repository)
    private val moveNoteToTrashUseCase = MoveNoteToTrashUseCase(repository)

    val notesList = getNotesListUseCase.getNotesList()

//    suspend fun getNotesList() {
//        notesList.value = getNotesListUseCase.getNotesList()
//    }

    suspend fun addNote(note: Note) {
        addNoteListUseCase.addNote(note)
    }

//    suspend fun inspectNote(id: Int) {
//        inspectedNote.value = inspectNoteUseCase.inspectNote(id)
//    }

    suspend fun moveNoteToArchive(id: Int) {
        moveNoteToArchiveUseCase.moveNoteToArchive(id)
    }

    suspend fun moveNoteToTrash(id: Int) {
        moveNoteToTrashUseCase.moveNoteToTrash(id)
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