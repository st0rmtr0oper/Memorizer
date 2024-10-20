package com.djentleman.memorizer.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.djentleman.memorizer.R
import com.djentleman.memorizer.databinding.FragmentNotesBinding
import com.djentleman.memorizer.domain.models.EditorMode
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.ui.utils.NotesAdapter
import com.google.android.material.snackbar.Snackbar

class NotesFragment : Fragment() {

    private val binding by lazy {
        FragmentNotesBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[NotesViewModel::class.java]
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
        setUpAddButton()
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
                        moveNoteToArchive(noteId)
                        val message = getString(R.string.swipe_snack_bar_to_archive)
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
            .setAction(getString(R.string.swipe_snack_bar_undo_button)) { moveNoteToActual(noteId) }
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
            notesAdapter.submitList(it)
        }
    }

    private fun setUpAddButton() {
        binding.fab.setOnClickListener {
            findNavController().navigate(
                NotesFragmentDirections.actionNotesToEditor(
                    EditorMode.ADD,
                    -1
                )
            )
        }
    }

    private fun showNoteDialog(note: Note) {
        AlertDialog.Builder(requireContext()).setItems(
            arrayOf(
                getString(R.string.dialogue_move_to_archive),
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
            NotesFragmentDirections.actionNotesToEditor(
                EditorMode.INSPECT,
                note.id
            )
        )
    }

    private fun editNote(note: Note) {
        findNavController().navigate(
            NotesFragmentDirections.actionNotesToEditor(
                EditorMode.EDIT,
                note.id
            )
        )
    }

    private fun moveNoteToArchive(id: Int) {
        viewModel.moveNoteToArchive(id)
    }

    private fun moveNoteToTrash(id: Int) {
        viewModel.moveNoteToTrash(id)
    }

    private fun moveNoteToActual(id: Int) {
        viewModel.moveNoteToActual(id)
    }
}


//{ view ->
//    Snackbar.make(view, "Тыкнуто", Snackbar.LENGTH_LONG)
//        .setAction("Action", null)
//        .setAnchorView(R.id.fab).show()
//}

//viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
//    activity?.onBackPressed()
//}
//  //we cant use finish() in fragment

//activity?  - nullable      - more safe
//requireActivity  - non nullable     - if null it crush app

