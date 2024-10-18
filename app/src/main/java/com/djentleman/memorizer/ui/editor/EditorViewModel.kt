package com.djentleman.memorizer.ui.editor

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.djentleman.memorizer.data.repository.MemorizerRepositoryImpl

class EditorViewModel(application: Application) : AndroidViewModel(application) {
    //TODO Это неправильно, pres зависит от data. лечится DI вроде как
    private val repository = MemorizerRepositoryImpl(application)
}