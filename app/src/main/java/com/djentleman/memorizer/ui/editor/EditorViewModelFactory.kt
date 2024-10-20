package com.djentleman.memorizer.ui.editor

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.djentleman.memorizer.domain.models.EditorMode

class EditorViewModelFactory(val application: Application, val mode: EditorMode, val noteId: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EditorViewModel(application, mode, noteId) as T
    }
}