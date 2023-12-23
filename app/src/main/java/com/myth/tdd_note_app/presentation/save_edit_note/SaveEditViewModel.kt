package com.myth.tdd_note_app.presentation.save_edit_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.usecases.UseCases
import com.myth.tdd_note_app.presentation.save_edit_note.events.AddEditEvent
import com.myth.tdd_note_app.presentation.save_edit_note.events.UiEvent
import com.myth.tdd_note_app.presentation.save_edit_note.states.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveEditViewModel @Inject constructor(
    private val useCase: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _noteTitle = mutableStateOf(TextFieldState())
    val noteTitle: State<TextFieldState> = _noteTitle

    private val _noteContent = mutableStateOf(TextFieldState())
    val noteContent: State<TextFieldState> = _noteContent

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    var currentNoteId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId").let { noteId ->
            if (noteId == -1) return@let

            currentNoteId = noteId
            viewModelScope.launch { getNote(noteId!!) }
        }
    }

    private suspend fun getNote(noteId: Int) {
        useCase.getNoteById(noteId)?.also { note ->
            updateNoteTitle(note.title)
            updateNoteContent(note.content)
        }
    }

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.EditNoteTitle -> {
                updateNoteTitle(event.noteTitle)
            }

            is AddEditEvent.EditNoteContent -> {
                updateNoteContent(event.noteContent)
            }

            is AddEditEvent.SaveNote -> {
                CoroutineScope(Dispatchers.IO).launch {
                    saveNote()
                    _eventFlow.emit(UiEvent.SaveNote)
                }
            }
        }
    }

    private fun updateNoteTitle(newTitle: String) {
        _noteTitle.value = noteTitle.value.copy(text = newTitle)
    }

    private fun updateNoteContent(newContent: String) {
        _noteContent.value = noteContent.value.copy(text = newContent)
    }

    private suspend fun saveNote() {
        val note = Note(
            id = currentNoteId,
            title = noteTitle.value.text,
            content = noteContent.value.text
        )
        useCase.addNote(note)
    }
}