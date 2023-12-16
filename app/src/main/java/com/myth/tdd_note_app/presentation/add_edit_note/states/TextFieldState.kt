package com.myth.tdd_note_app.presentation.add_edit_note.states

data class TextFieldState(
    val text: String = "",
    val hint: String = "",
    val showHint: Boolean = false
)