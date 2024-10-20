package com.djentleman.memorizer.di

import android.app.Application
import com.djentleman.memorizer.MainActivity
import com.djentleman.memorizer.ui.archive.ArchiveFragment
import com.djentleman.memorizer.ui.notes.NotesFragment
import com.djentleman.memorizer.ui.trash.TrashFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: NotesFragment)

    fun inject(fragment: ArchiveFragment)

    fun inject(fragment: TrashFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
