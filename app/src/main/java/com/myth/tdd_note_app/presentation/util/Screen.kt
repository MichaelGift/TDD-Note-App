package com.myth.tdd_note_app.presentation.util

sealed class Screen(val route: String)  {
    data object NotesScreen: Screen("notes_screen")
    data object SaveEditScreen: Screen("save_edit_screen")
}
