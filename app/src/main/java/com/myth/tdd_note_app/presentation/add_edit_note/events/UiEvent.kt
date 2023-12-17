package com.myth.tdd_note_app.presentation.add_edit_note.events

sealed class UiEvent {
    data class ShowSnackBar(val message: String) : UiEvent()
    object SaveNote : UiEvent()
    object DeleteNote : UiEvent()
}
