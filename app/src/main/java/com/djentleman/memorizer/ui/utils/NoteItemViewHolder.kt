package com.djentleman.memorizer.ui.utils

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.djentleman.memorizer.R

class NoteItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val noteHeader: TextView = view.findViewById(R.id.note_header)
    val noteContent: TextView = view.findViewById(R.id.note_content)
    val statusBar: ProgressBar = view.findViewById(R.id.status_bar)
}