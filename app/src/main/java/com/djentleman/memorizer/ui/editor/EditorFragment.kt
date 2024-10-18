package com.djentleman.memorizer.ui.editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.djentleman.memorizer.databinding.FragmentNoteEditorBinding
import com.djentleman.memorizer.domain.models.EditorMode
import com.djentleman.memorizer.domain.models.Note

class EditorFragment : Fragment() {

    private val binding by lazy {
        FragmentNoteEditorBinding.inflate(layoutInflater)
    }
    private val args by navArgs<EditorFragmentArgs>()
    private val viewModel by lazy {
        ViewModelProvider(this)[EditorViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkMode()
        setUpObservers()
    }

    private fun checkMode() {
        val editorMode = args.editorMode
        val noteId = args.noteId
        when (editorMode) {
            EditorMode.ADD -> {
//                getDraft()
//                enableEditMode()
            }

            EditorMode.EDIT -> {
//                getNote(noteId)
//                enableEditMode()
            }

            EditorMode.INSPECT -> {
//                getNote(noteId)
//                enableInspectMode()
            }
        }
    }

    private fun setUpObservers() {
        viewModel.note.observe(viewLifecycleOwner) {
            setUpNote(it)
        }
    }

    private fun getDraft() {
        viewModel.loadDraft()
    }

    private fun getNote(noteId: Int) {
        viewModel.loadNote(noteId)
    }

    private fun enableEditMode() {
        //editable true
        //buttons
    }

    private fun enableInspectMode() {
        //editable false
        //buttons
    }

    private fun setUpNote(note: Note) {
        with(binding) {
            noteHeaderEdit.setText(note.header)
            noteContentEdit.setText(note.content)
            //TODO other elements in future
        }
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