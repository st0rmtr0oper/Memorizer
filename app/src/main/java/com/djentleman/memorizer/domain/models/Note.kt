package com.djentleman.memorizer.domain.models

//TODO
data class Note(
    val header: String,
    val content: String,
    val tags: List<String>,
    val noteStatus: NoteStatus,
    val image: List<String>,

    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
