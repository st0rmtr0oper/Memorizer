package com.djentleman.memorizer.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.djentleman.memorizer.domain.models.NoteStatus

@Entity(tableName = "notes")
data class NoteDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val header: String,
    val content: String,
    val tags: String,
    val noteStatus: NoteStatus,
    val image: String
)