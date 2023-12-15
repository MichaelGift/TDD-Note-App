package com.myth.tdd_note_app.domain.usecases

import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.repository.NoteRepository

class GetNoteByIdUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(id: Int): Note? = repository.getNoteById(id)
}