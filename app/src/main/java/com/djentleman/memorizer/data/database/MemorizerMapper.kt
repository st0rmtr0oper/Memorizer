package com.djentleman.memorizer.data.database

import com.djentleman.memorizer.domain.models.Note
import javax.inject.Inject

class MemorizerMapper @Inject constructor() {

    fun mapEntityToDbModel(note: Note) = NoteDbModel(
        id = note.id,
        header = note.header,
        content = note.content,
        noteStatus = note.noteStatus
    )

    fun mapDbModelToEntity(noteDbModel: NoteDbModel) = Note(
        id = noteDbModel.id,
        header = noteDbModel.header,
        content = noteDbModel.content,
        noteStatus = noteDbModel.noteStatus
    )

    fun mapListDbModelToListEntity(list: List<NoteDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}