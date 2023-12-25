package com.myth.tdd_note_app.domain.usecases

data class UseCases(
    val addNote: AddNotesUseCase,
    val deleteNote : DeleteNoteUseCase,
    val getAllNotes: GetNotesUseCase,
    val getNoteById: GetNoteByIdUseCase,
)
