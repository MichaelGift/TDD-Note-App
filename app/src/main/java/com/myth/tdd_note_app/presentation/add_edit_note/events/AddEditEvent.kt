package com.myth.tdd_note_app.presentation.add_edit_note.events

import androidx.compose.ui.focus.FocusState

sealed class AddEditEvent {
    data class EditNoteTitle(val noteTitle: String) : AddEditEvent()
    data class ChangeTitleFocus(val focusState: FocusState) : AddEditEvent()
    data class EditNoteContent(val noteContent: String) : AddEditEvent()
    data class ChangeContentFocus(val focusState: FocusState) : AddEditEvent()
    object SaveNote : AddEditEvent()
}
