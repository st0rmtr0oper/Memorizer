package com.djentleman.memorizer.di

import androidx.lifecycle.ViewModel
import com.djentleman.memorizer.ui.archive.ArchiveViewModel
import com.djentleman.memorizer.ui.editor.EditorViewModel
import com.djentleman.memorizer.ui.notes.NotesViewModel
import com.djentleman.memorizer.ui.trash.TrashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NotesViewModel::class)
    fun bindNotesViewModel(viewModel: NotesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ArchiveViewModel::class)
    fun bindArchiveViewModel(viewModel: ArchiveViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TrashViewModel::class)
    fun bindTrashViewModel(viewModel: TrashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EditorViewModel::class)
    fun bindEditorViewModel(viewModel: EditorViewModel): ViewModel
}