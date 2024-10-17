package com.djentleman.memorizer.data.database

import com.djentleman.memorizer.domain.models.Note

class MemorizerMapper {

    fun mapEntityToDbModel(note: Note) = NoteDbModel(
        id = note.id,
        header = note.header,
        content = note.content,
        tags = note.tags,
        noteStatus = note.noteStatus,
        image = note.image
    )

    fun mapDbModelToEntity(noteDbModel: NoteDbModel) = Note(
        id = noteDbModel.id,
        header = noteDbModel.header,
        content = noteDbModel.content,
        tags = noteDbModel.tags,
        noteStatus = noteDbModel.noteStatus,
        image = noteDbModel.image
    )

    fun mapListDbModelToListEntity(list: List<NoteDbModel>) = list.map {
        mapDbModelToEntity(it)
    }
}