package com.djentleman.memorizer.ui.notes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.djentleman.memorizer.data.repository.MemorizerRepositoryImpl
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.usecases.AddNoteUseCase
import com.djentleman.memorizer.domain.usecases.DeleteNoteUseCase
import com.djentleman.memorizer.domain.usecases.GetNotesListUseCase
import com.djentleman.memorizer.domain.usecases.InspectNoteUseCase

class NotesViewModel : ViewModel() {
    //TODO Это неправильно, pres зависит от data
    private val repository = MemorizerRepositoryImpl
    private val getNotesListUsecase = GetNotesListUseCase(repository)
    private val addNotesListUsecase = AddNoteUseCase(repository)
    private val deleteNoteUsecase = DeleteNoteUseCase(repository)
    private val inspectNoteUsecase = InspectNoteUseCase(repository)

    val list = MutableLiveData<List<Note>>()
    val inspectedNote = MutableLiveData<Note>()

    fun getNotesList() {
        val newList = getNotesListUsecase.getNotesList()
        list.value = newList
    }

    fun deleteNote(note: Note) {
        deleteNoteUsecase.deleteNote(note)
        getNotesList()
    }

    fun addNote(note: Note) {
        addNotesListUsecase.addNote(note)
        getNotesList()
    }

    fun inspectNote(id: Int) {
        val note = inspectNoteUsecase.inspectNote(id)
        inspectedNote.value = note
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