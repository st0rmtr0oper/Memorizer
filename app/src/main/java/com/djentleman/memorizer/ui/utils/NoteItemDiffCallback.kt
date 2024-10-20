package com.djentleman.memorizer.ui.utils

import androidx.recyclerview.widget.DiffUtil
import com.djentleman.memorizer.domain.models.Note

class NoteItemDiffCallback : DiffUtil.ItemCallback<Note>() {

    override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
        return oldItem == newItem
    }
}