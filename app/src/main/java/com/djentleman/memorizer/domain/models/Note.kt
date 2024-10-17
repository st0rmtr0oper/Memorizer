package com.djentleman.memorizer.domain.models

//TODO
data class Note(
    val header: String,
    val content: String,
    val tags: String,
    val noteStatus: NoteStatus,
    val image: String,
    val id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
