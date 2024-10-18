package com.djentleman.memorizer.ui.trash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.djentleman.memorizer.databinding.FragmentTrashBinding
import com.djentleman.memorizer.ui.utils.NotesAdapter

class TrashFragment : Fragment() {

    private val binding by lazy {
        FragmentTrashBinding.inflate(layoutInflater)
    }
    private lateinit var viewModel: TrashViewModel
    private lateinit var trashAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[TrashViewModel::class.java]
        return binding.root
    }
}