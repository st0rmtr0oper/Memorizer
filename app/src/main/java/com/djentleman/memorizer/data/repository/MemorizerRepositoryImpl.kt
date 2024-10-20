package com.djentleman.memorizer.data.repository

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.djentleman.memorizer.data.database.MemorizerDao
import com.djentleman.memorizer.data.database.MemorizerDatabase
import com.djentleman.memorizer.data.database.MemorizerMapper
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.models.NoteStatus
import com.djentleman.memorizer.domain.repository.MemorizerRepository
import javax.inject.Inject

class MemorizerRepositoryImpl @Inject constructor(
    private val memorizerDao: MemorizerDao,
    private val mapper: MemorizerMapper,
    private val sharedPreferences: SharedPreferences
) : MemorizerRepository {

    companion object {
        const val PREFS_NAME = "my_prefs"
        const val NOTE_HEADER = "note_header"
        const val NOTE_CONTENT = "note_content"
        const val NOTE_TAGS = "note_tags"
    }

    override fun getNotesList(): LiveData<List<Note>> = memorizerDao.getNotesList().map {
        mapper.mapListDbModelToListEntity(it)
    }

    override fun getArchivedList(): LiveData<List<Note>> = memorizerDao.getArchivedList().map {
        mapper.mapListDbModelToListEntity(it)
    }

    override fun getTrashList(): LiveData<List<Note>> = memorizerDao.getTrashList().map {
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun addNote(note: Note) {
        sharedPreferences.edit().clear().apply()
        memorizerDao.addNote(mapper.mapEntityToDbModel(note))
    }

    override suspend fun deleteNote(id: Int) {
        memorizerDao.deleteNote(id)
    }


    override fun loadNote(id: Int): LiveData<Note> = memorizerDao.loadNote(id).map {
        mapper.mapDbModelToEntity(it)
    }

    override suspend fun saveNote(note: Note) {
        memorizerDao.saveNote(mapper.mapEntityToDbModel(note))
    }

    override suspend fun moveNoteToActual(id: Int) {
        memorizerDao.moveNoteToActual(id)
    }

    override suspend fun moveNoteToArchive(id: Int) {
        memorizerDao.moveNoteToArchive(id)
    }

    override suspend fun moveNoteToTrash(id: Int) {
        memorizerDao.moveNoteToTrash(id)
    }

    override suspend fun clearAllTrash() {
        memorizerDao.clearAllTrash()
    }

    override fun saveDraft(note: Note) {
        sharedPreferences.edit()
            .putString(NOTE_HEADER, note.header)
            .putString(NOTE_CONTENT, note.content)
            .apply()
    }

    override fun loadDraft(): LiveData<Note> {
        val header: String
        val content: String
        val tags: String
        with(sharedPreferences) {
            header = getString(NOTE_HEADER, "").toString()
            content = getString(NOTE_CONTENT, "").toString()
            tags = getString(NOTE_TAGS, "").toString()
        }
        val note = Note(header, content, NoteStatus.ACTUAL)
        return MutableLiveData(note)
    }
}