package com.djentleman.memorizer.ui.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.djentleman.memorizer.databinding.NoteRecyclerItemBinding
import com.djentleman.memorizer.domain.models.Note

class NotesAdapter : ListAdapter<Note, NoteItemViewHolder>(NoteItemDiffCallback()) {

    var onNoteItemClickListener: ((Note) -> Unit)? = null
    var onNoteItemLongClickListener: ((Note) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteItemViewHolder {
        val binding = NoteRecyclerItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteItemViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: NoteItemViewHolder, position: Int) {
        val note = getItem(position)

        with(viewHolder.binding) {
            root.setOnClickListener {
                onNoteItemClickListener?.invoke(note)
            }
            root.setOnLongClickListener {
                onNoteItemLongClickListener?.invoke(note)
                true
            }
            noteHeader.text = note.header
            noteContent.text = note.content
            statusBar.setBackgroundColor(getNoteStatusColor(note.noteStatus))
        }
    }

    companion object {
        const val VIEW_TYPE = 100
        const val MAX_POOL_SIZE = 30
    }
}