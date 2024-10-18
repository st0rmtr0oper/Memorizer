package com.djentleman.memorizer.ui.archive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.djentleman.memorizer.databinding.FragmentArchiveBinding
import com.djentleman.memorizer.ui.utils.NotesAdapter

class ArchiveFragment : Fragment() {

    private val binding by lazy {
        FragmentArchiveBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[ArchiveViewModel::class.java]
    }
    private lateinit var archiveAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}