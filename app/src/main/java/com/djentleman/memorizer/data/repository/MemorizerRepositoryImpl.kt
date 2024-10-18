package com.djentleman.memorizer.data.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.djentleman.memorizer.data.database.MemorizerDatabase
import com.djentleman.memorizer.data.database.MemorizerMapper
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.models.NoteStatus
import com.djentleman.memorizer.domain.repository.MemorizerRepository

class MemorizerRepositoryImpl(application: Application) : MemorizerRepository {

    companion object {
        const val PREFS_NAME = "my_prefs"
        const val NOTE_HEADER = "note_header"
        const val NOTE_CONTENT = "note_content"
        const val NOTE_TAGS = "note_tags"
//        const val NOTE_IMAGE = "note_image"
    }

    private val memorizerDao = MemorizerDatabase.getInstance(application).memorizerDao()
    private val mapper = MemorizerMapper()
    private val sharedPreferences = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    override fun getNotesList(): LiveData<List<Note>> = memorizerDao.getNotesList().map {
        mapper.mapListDbModelToListEntity(it)
    }

    override fun getArchivedList(): LiveData<List<Note>> = memorizerDao.getArchivedList().map {
        mapper.mapListDbModelToListEntity(it)
    }

    override fun getTrashList(): LiveData<List<Note>> = memorizerDao.getTrashList().map {
        mapper.mapListDbModelToListEntity(it)
    }

    // TODO надо проверить как автообновление работает
//    private fun updateList() {
//        ??????????????
//    }  2:52

    override suspend fun addNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun loadNote(id: Int): Note {
        TODO("Not yet implemented")
    }

    override suspend fun saveNote(note: Note) {
        TODO("Not yet implemented")
    }

    override suspend fun moveNoteToActual(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun moveNoteToArchive(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun moveNoteToTrash(id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun clearAllTrash() {
        TODO("Not yet implemented")
    }

    override fun saveDraft(note: Note) {
        sharedPreferences.edit()
            .putString(NOTE_HEADER, note.header)
            .putString(NOTE_CONTENT, note.content)
            .putString(NOTE_TAGS, note.tags)
//            .putString(NOTE_IMAGE, note.image)
            .apply()
    }

    override fun loadDraft(): Note {
        val header: String
        val content: String
        val tags: String
        with(sharedPreferences) {
            header = getString(NOTE_HEADER, null).toString()
            content = getString(NOTE_CONTENT, null).toString()
            tags = getString(NOTE_TAGS, null).toString()
//            val image = getString(NOTE_IMAGE, "")
        }
        return Note(header, content, tags, NoteStatus.ACTUAL, "")
    }
}