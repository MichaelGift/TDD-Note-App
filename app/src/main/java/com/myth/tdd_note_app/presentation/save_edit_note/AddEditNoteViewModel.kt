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
class AddEditNoteViewModel @Inject constructor(
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
            viewModelScope.launch {
                getNote(noteId!!)
            }
        }
    }

    private suspend fun getNote(noteId: Int) {
        useCase.getNoteById(noteId)?.also { note ->
            _noteTitle.value = noteTitle.value.copy(text = note.title)
            _noteContent.value = noteContent.value.copy(text = note.content)
        }
    }

    private suspend fun saveNote() {
        useCase.addNote(
            Note(
                id = currentNoteId,
                title = noteTitle.value.text,
                content = noteContent.value.text
            )
        )
    }

    fun onEvent(event: AddEditEvent) {
        when (event) {
            is AddEditEvent.EditNoteTitle -> {
                _noteTitle.value = noteTitle.value.copy(text = event.noteTitle)
            }

            is AddEditEvent.EditNoteContent -> {
                _noteContent.value = noteContent.value.copy(text = event.noteContent)
            }

            is AddEditEvent.SaveNote -> {
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        saveNote()
                        _eventFlow.emit(UiEvent.SaveNote)
                    } catch (_: Exception) {
                    }
                }
            }
        }
    }
}