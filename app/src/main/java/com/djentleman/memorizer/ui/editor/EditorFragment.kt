package com.djentleman.memorizer.ui.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.djentleman.memorizer.databinding.FragmentNoteEditorBinding
import com.djentleman.memorizer.domain.models.EditorMode
import com.djentleman.memorizer.domain.models.Note
import com.djentleman.memorizer.domain.models.NoteStatus

class EditorFragment : Fragment() {

    private val binding by lazy {
        FragmentNoteEditorBinding.inflate(layoutInflater)
    }
    private val args by navArgs<EditorFragmentArgs>()

    private lateinit var viewModel: EditorViewModel
    private lateinit var mode: EditorMode
    private var noteId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //не знаю насколько правильно решение делать именно так, но вроде работает
        mode = args.editorMode
        noteId = args.noteId
        viewModel = ViewModelProvider(
            this,
            EditorViewModelFactory(requireActivity().application, mode, noteId)
        )[EditorViewModel::class.java]

        setUpObservers()
        setUpButtons()
        setUpOnBackListener()
        checkMode()
    }

    private fun checkMode() {
        val editorMode = args.editorMode
        val noteId = args.noteId
        when (editorMode) {
            EditorMode.ADD -> {
                getDraft()
                enableAddMode()
                mode = EditorMode.ADD
            }

            EditorMode.EDIT -> {
                getNote(noteId)
                enableEditMode()
                mode = EditorMode.EDIT
            }

            EditorMode.INSPECT -> {
                getNote(noteId)
                enableInspectMode()
                mode = EditorMode.INSPECT
            }
        }
    }

    private fun setUpObservers() {
        viewModel.note.observe(viewLifecycleOwner) {
            setUpNote(it)
        }
    }

    private fun enableAddMode() {
        with(binding) {
            noteHeaderEdit.isEnabled = true
            noteContentEdit.isEnabled = true
            fabAdd.isVisible = true
            fabEdit.isGone = true
            fabSave.isGone = true
        }
    }

    private fun enableEditMode() {
        with(binding) {
            noteHeaderEdit.isEnabled = true
            noteContentEdit.isEnabled = true
            fabAdd.isGone = true
            fabEdit.isGone = true
            fabSave.isVisible = true
        }
    }

    private fun enableInspectMode() {
        with(binding) {
            noteHeaderEdit.isEnabled = false
            noteContentEdit.isEnabled = false
            fabAdd.isGone = true
            fabEdit.isVisible = true
            fabSave.isGone = true
        }
    }

    private fun setUpNote(note: Note) {
        with(binding) {
            noteHeaderEdit.setText(note.header)
            noteContentEdit.setText(note.content)
            //TODO other elements in future
        }
    }

    private fun setUpButtons() {

        with(binding) {
            fabSave.setOnClickListener {
                val editedNote = Note(
                    binding.noteHeaderEdit.text.toString(),
                    binding.noteContentEdit.text.toString(),
                    //TODO looks like shit???
                    "",
                    viewModel.note.value!!.noteStatus,
                    "",
                    noteId
//                    getNoteStatus()
                    //TODO it should work in other way???
//                    viewModel.note.value.tags,
//                    viewModel.note.value.noteStatus,
                )
                saveNote(editedNote)
                enableInspectMode()
                //what with callbacks? do i need other observable val in viewmodel for updating?
            }
            fabEdit.setOnClickListener {
                enableEditMode()
            }
            fabAdd.setOnClickListener {
                val newNote = Note(
                    binding.noteHeaderEdit.text.toString(),
                    binding.noteContentEdit.text.toString(),
                    "",
                    NoteStatus.ACTUAL,
                    ""
                )
                addNote(newNote)
                findNavController().popBackStack()
            }
        }
    }

    private fun setUpOnBackListener() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            handleBackPress()
        }
        //TODO up button
    }

    private fun handleBackPress() {
        when (mode) {
            EditorMode.ADD -> {
                val draftNote = Note(
                    binding.noteHeaderEdit.text.toString(),
                    binding.noteContentEdit.text.toString(),
                    "",
                    NoteStatus.ACTUAL,
                    ""
                )
                saveDraft(draftNote)
                findNavController().popBackStack()
            }

            EditorMode.EDIT -> {
                showUnsavedExitSnackBar()
            }

            EditorMode.INSPECT -> {
                findNavController().popBackStack()

            }
        }
    }

    private fun showUnsavedExitSnackBar() {
//        { view ->
//    Snackbar.make(view, "Тыкнуто", Snackbar.LENGTH_LONG)
//        .setAction("Action", null)
//        .setAnchorView(R.id.fab).show()
//}
    }

//    private fun getNoteStatus(): NoteStatus {
//        val value = viewModel.note.value
//        return value?.noteStatus ?: NoteStatus.ACTUAL
//    }

    private fun getDraft() {
        viewModel.loadDraft()
    }

    private fun saveDraft(note: Note) {
        viewModel.saveDraft(note)
    }

    private fun getNote(noteId: Int) {
        viewModel.loadNote(noteId)
    }

    private fun saveNote(note: Note) {
        viewModel.saveNote(note)
    }

    private fun addNote(note: Note) {
        viewModel.addNote(note)
    }
}

//логика простая:
//фрагмент создается, из нав бандла(?) получается режим работы
//если раежим работы ADD
//запускаем в режиме редактирования, fab имеет вид "добавить". если есть данные в SP, закидываем в поля
//при нажатии на fab заметка создается и юзер выходит с экрана на главный
//при простом выходе назад данные кидаются в SP, юзер выходит на главный
//
//        если режим работы INSPECT
//        запускаем в режиме просмотра, загружаем данные в поля, fab имеет вид "редактировать"
//        при выходе назад тупо выходим на главный
//        при нажатии на fab заметка переходит в режим редактирования (меняем на EDIT)
//
//        если режим работы EDIT
//        запускаем в режиме редактирвания, загружаем данные в поля, fab имеет вид "сохранить"
//        при выходе назад вылетает снак бар с предупреждением и кнопокой "сохранить"
//        при повторном нажатии юзер вылетает на главный экран без изменений
//        при нажатии на кнопку "сохранить" меняется запись в БД, юзер получает снакбар с успехом и вылетает на главную