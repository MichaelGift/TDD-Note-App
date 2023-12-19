package com.myth.tdd_note_app.presentation.notes_list.states

import com.myth.tdd_note_app.domain.model.Note

data class NotesState(val notes: List<Note> = emptyList())
