package com.djentleman.memorizer.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.djentleman.memorizer.domain.models.NoteStatus

@Dao
interface MemorizerDao {

    @Query("SELECT * FROM notes WHERE noteStatus =:status")
    fun getNotesList(status: NoteStatus = NoteStatus.ACTUAL): LiveData<List<NoteDbModel>>

    @Query("SELECT * FROM notes WHERE noteStatus = :status")
    fun getArchivedList(status: NoteStatus = NoteStatus.ARCHIVED): LiveData<List<NoteDbModel>>

    @Query("SELECT * FROM notes WHERE noteStatus = :status")
    fun getTrashList(status: NoteStatus = NoteStatus.TRASHED): LiveData<List<NoteDbModel>>

    @Insert
    suspend fun addNote(noteDbModel: NoteDbModel)

    //    @Update
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(noteDbModel: NoteDbModel)

    @Query("DELETE FROM notes WHERE id = :noteId")
    suspend fun deleteNote(noteId: Int)

    @Query("SELECT * FROM notes WHERE id = :noteId LIMIT 1")
    fun loadNote(noteId: Int): LiveData<NoteDbModel>

    @Query("UPDATE notes SET noteStatus = :newStatus WHERE id = :noteId")
    suspend fun moveNoteToActual(noteId: Int, newStatus: NoteStatus = NoteStatus.ACTUAL)

    @Query("UPDATE notes SET noteStatus = :newStatus WHERE id = :noteId")
    suspend fun moveNoteToArchive(noteId: Int, newStatus: NoteStatus = NoteStatus.ARCHIVED)

    @Query("UPDATE notes SET noteStatus = :newStatus WHERE id = :noteId")
    suspend fun moveNoteToTrash(noteId: Int, newStatus: NoteStatus = NoteStatus.TRASHED)

    @Query("DELETE FROM notes WHERE noteStatus = :status")
    suspend fun clearAllTrash(status: NoteStatus = NoteStatus.TRASHED)
}