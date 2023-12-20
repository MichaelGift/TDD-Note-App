package com.myth.tdd_note_app.presentation.add_edit_note.events

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
    data object SaveNote : UiEvent()
    data class DeleteNote(val message: String) : UiEvent()
}
