package com.myth.tdd_note_app.presentation.notes_list.events

import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.presentation.notes_list.states.SearchWidgetState

sealed class NoteEvent {
    data class DeleteNote(val note: Note) : NoteEvent()
    data class SearchNote(val query: String) : NoteEvent()
    data object RestoreNote : NoteEvent()
    data class ToggleSearchBar(val value: SearchWidgetState) : NoteEvent()
}