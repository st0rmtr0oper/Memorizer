package com.djentleman.memorizer.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.djentleman.memorizer.R
import com.djentleman.memorizer.domain.models.Note

class NotesAdapter : ListAdapter<Note, NoteItemViewHolder>(NoteItemDiffCallback()) {

    var onNoteItemClickListener: ((Note) -> Unit)? = null
    var onNoteItemLongClickListener: ((Note) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.note_recycler_item,
                parent,
                false
            )
        return NoteItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: NoteItemViewHolder, position: Int) {
        val note = getItem(position)
        viewHolder.view.setOnClickListener {
            onNoteItemClickListener?.invoke(note)
        }
        viewHolder.view.setOnLongClickListener {
            onNoteItemLongClickListener?.invoke(note)
            true
        }
        viewHolder.noteHeader.text = note.header
        viewHolder.noteContent.text = note.content
        viewHolder.statusBar.setBackgroundColor(getNoteStatusColor(note.noteStatus))
        viewHolder.itemView
    }

    companion object {
        const val VIEW_TYPE = 100
        const val MAX_POOL_SIZE = 30
    }
}