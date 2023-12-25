package com.myth.tdd_note_app.presentation.notes_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.usecases.UseCases
import com.myth.tdd_note_app.presentation.notes_list.events.NoteEvent
import com.myth.tdd_note_app.presentation.notes_list.states.NotesState
import com.myth.tdd_note_app.presentation.save_edit_note.events.UiEvent
import com.myth.tdd_note_app.presentation.notes_list.states.SearchWidgetState
import com.myth.tdd_note_app.presentation.save_edit_note.states.TextFieldState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesListViewModel @Inject constructor(
    private val useCase: UseCases,
) : ViewModel() {
    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state
    private val _query = mutableStateOf(TextFieldState())
    val query: State<TextFieldState> = _query

    private val _searchWidgetState = mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState = _searchWidgetState

    private val _eventFlow = MutableSharedFlow<UiEvent>()

    //    val eventFlow = _eventFlow.asSharedFlow()
    private var recentlyDeletedNote: Note? = null

    init {
        viewModelScope.launch {
            getNotes()
        }
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.DeleteNote -> {
                CoroutineScope(Dispatchers.IO).launch {
                    deleteNote(event.note)
                    recentlyDeletedNote = event.note
                    _eventFlow.emit(UiEvent.DeleteNote("Note Deleted"))
                }
            }

            is NoteEvent.RestoreNote -> {
                CoroutineScope(Dispatchers.IO).launch {
                    useCase.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }
            }

            is NoteEvent.SearchNote -> {
                _query.value = query.value.copy(text = event.query)
//                CoroutineScope(Dispatchers.IO).launch {
//                    useCase.search(event.query).collect { notes ->
//                        _state.value = state.value.copy(notes = notes)
//                    }
//                }
            }

            is NoteEvent.ToggleSearchBar -> {
                _searchWidgetState.value = event.value
            }
        }
    }

    private suspend fun deleteNote(note: Note) {
        useCase.deleteNote(note)
    }

    suspend fun getNotes() {
        useCase.getAllNotes().collect { notes ->
            _state.value = state.value.copy(notes = notes)
        }
    }
}