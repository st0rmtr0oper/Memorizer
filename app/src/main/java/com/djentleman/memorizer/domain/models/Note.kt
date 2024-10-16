package com.djentleman.memorizer.domain.models

//TODO
data class Note(
    val fileName: String,
    val fileMd: Int,

    var id: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = -1
    }
}
