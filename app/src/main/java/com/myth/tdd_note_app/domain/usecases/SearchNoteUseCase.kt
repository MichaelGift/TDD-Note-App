package com.myth.tdd_note_app.domain.usecases

import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class SearchNoteUseCase(private val repository: NoteRepository) {
    operator fun invoke(query:String): Flow<List<Note>> = repository.searchNotes(query)
}