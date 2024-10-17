package com.djentleman.memorizer.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.djentleman.memorizer.databinding.FragmentNotesBinding
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.ui.utils.NotesAdapter

class NotesFragment : Fragment() {

    //TODO what to do with binding
    private var _binding: FragmentNotesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: NotesViewModel
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[NotesViewModel::class.java]
        _binding = FragmentNotesBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpRecyclerView() {
        with(binding.rvNotes) {
            notesAdapter = NotesAdapter()
            adapter = notesAdapter
            recycledViewPool.setMaxRecycledViews(
                NotesAdapter.VIEW_TYPE,
                NotesAdapter.MAX_POOL_SIZE
            )
        }
        setUpRvClickListener()
        setUpRvLongClickListener()
        setUpRvSwipeListener()
    }

    private fun setUpRvSwipeListener() {
        val callback = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val noteId = notesAdapter.currentList[viewHolder.adapterPosition].id
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        moveNoteToTrash(noteId)
                    }

                    ItemTouchHelper.RIGHT -> {
                        moveNoteToArchive(noteId)
                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvNotes)
    }

    private fun setUpRvLongClickListener() {
        notesAdapter.onNoteItemLongClickListener = { showNoteDialog(it) }
    }

    private fun setUpRvClickListener() {
        notesAdapter.onNoteItemClickListener = { inspectNote(it) }
    }

    private fun setUpObservers() {
        viewModel.notesList.observe(viewLifecycleOwner) {
            notesAdapter.submitList(it)
        }
    }

    private fun inspectNote(note: Note) {
        TODO()
        //viewmodel.inspect?
        //navigation
    }

    private fun showNoteDialog(note: Note) {
        TODO()
        //showdialog
    }

    private fun moveNoteToArchive(id: Int) {
        TODO()
    }

    private fun moveNoteToTrash(id: Int) {
        TODO()
    }
}