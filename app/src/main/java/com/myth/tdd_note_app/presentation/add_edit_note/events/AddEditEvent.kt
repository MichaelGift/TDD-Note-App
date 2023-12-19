package com.myth.tdd_note_app.presentation.add_edit_note.events

sealed class AddEditEvent {
    data class EditNoteTitle(val noteTitle: String) : AddEditEvent()
    data class EditNoteContent(val noteContent: String) : AddEditEvent()
    data object SaveNote : AddEditEvent()
}
