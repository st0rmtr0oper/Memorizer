package com.djentleman.memorizer.ui.utils

import android.graphics.Color
import com.djentleman.memorizer.domain.models.NoteStatus

fun getNoteStatusColor(noteStatus: NoteStatus): Int {
    return when (noteStatus) {
        NoteStatus.ACTUAL -> Color.parseColor("#A4D65E")
        NoteStatus.ARCHIVED -> Color.YELLOW
        NoteStatus.TRASHED -> Color.RED
    }
}