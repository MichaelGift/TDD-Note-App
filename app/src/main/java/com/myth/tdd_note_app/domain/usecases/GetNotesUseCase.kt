package com.myth.tdd_note_app.domain.usecases

import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotesUseCase(private val repository: NoteRepository) {
    operator fun invoke(): Flow<List<Note>>  = repository.getAllNotes()
}