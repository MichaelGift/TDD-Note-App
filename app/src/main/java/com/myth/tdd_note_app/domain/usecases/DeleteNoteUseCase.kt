package com.myth.tdd_note_app.domain.usecases

import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.repository.NoteRepository

class DeleteNoteUseCase(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note){
        repository.deleteNote(note)
    }
}