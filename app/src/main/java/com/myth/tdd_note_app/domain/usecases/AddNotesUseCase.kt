package com.myth.tdd_note_app.domain.usecases

import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.repository.NoteRepository

class AddNotesUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note)  = repository.insertNote(note)
}