package com.djentleman.memorizer.domain.models

data class Note(
    val header: String,
    val content: String,
    val noteStatus: NoteStatus,
    val id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
