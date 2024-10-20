package com.djentleman.memorizer.ui.archive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.djentleman.memorizer.R
import com.djentleman.memorizer.databinding.FragmentArchiveBinding
import com.djentleman.memorizer.domain.models.EditorMode
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.ui.utils.NotesAdapter
import com.google.android.material.snackbar.Snackbar

class ArchiveFragment : Fragment() {

    private val binding by lazy {
        FragmentArchiveBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[ArchiveViewModel::class.java]
    }
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObservers()
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
                        val message = getString(R.string.swipe_snack_bar_to_trash)
                        showSnackBar(noteId, message)
                    }

                    ItemTouchHelper.RIGHT -> {
                        moveNoteToActual(noteId)
                        val message = getString(R.string.swipe_snack_bar_to_actual)
                        showSnackBar(noteId, message)
                    }
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(binding.rvNotes)
    }

    private fun showSnackBar(noteId: Int, message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setAction(getString(R.string.swipe_snack_bar_undo_button)) { moveNoteToArchive(noteId) }
            .show()
    }

    private fun setUpRvLongClickListener() {
        notesAdapter.onNoteItemLongClickListener = { showNoteDialog(it) }
    }

    private fun setUpRvClickListener() {
        notesAdapter.onNoteItemClickListener = { inspectNote(it) }
    }

    private fun setUpObservers() {
        viewModel.notesList.observe(viewLifecycleOwner) {
            if (viewModel.notesList.value!!.isEmpty()) {
                showPlug()
            } else {
                showRecyclerView()
                notesAdapter.submitList(it)
            }        }
    }

    private fun showPlug() {
        with(binding) {
            rvNotes.isGone = true
            plug.isVisible = true
        }
    }

    private fun showRecyclerView() {
        with(binding) {
            rvNotes.isVisible = true
            plug.isGone = true
        }
    }


    private fun showNoteDialog(note: Note) {
        AlertDialog.Builder(requireContext()).setItems(
            arrayOf(
                getString(R.string.dialogue_move_to_actual),
                getString(R.string.dialogue_move_to_trash),
                getString(R.string.dialogue_inspect),
                getString(R.string.dialogue_edit)
            )
        ) { _, which ->
            when (which) {
                0 -> moveNoteToArchive(note.id)
                1 -> moveNoteToTrash(note.id)
                2 -> inspectNote(note)
                3 -> editNote(note)
            }
        }.create().show()
    }

    private fun inspectNote(note: Note) {
        findNavController().navigate(
            ArchiveFragmentDirections.actionNavArchiveToNavEdit(
                EditorMode.INSPECT,
                note.id
            )
        )
    }

    private fun editNote(note: Note) {
        findNavController().navigate(
            ArchiveFragmentDirections.actionNavArchiveToNavEdit(
                EditorMode.EDIT,
                note.id
            )
        )
    }

    private fun moveNoteToActual(id: Int) {
        viewModel.moveNoteToActual(id)
    }

    private fun moveNoteToTrash(id: Int) {
        viewModel.moveNoteToTrash(id)
    }

    private fun moveNoteToArchive(id: Int) {
        viewModel.moveNoteToArchive(id)
    }
}