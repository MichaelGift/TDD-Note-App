package com.myth.tdd_note_app.data.repository

import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : NoteRepository {
    private val notes = mutableListOf<Note>()

    override fun getAllNotes(): Flow<List<Note>> = flow { emit(notes) }

    override suspend fun getNoteById(id: Int): Note? {
        return notes.firstOrNull { note ->  note.id == id }
    }

    override suspend fun insertNote(note: Note) {
        notes.add(note)
    }

    override suspend fun deleteNote(note: Note) {
        notes.remove(note)
    }

    fun addSampleNotes(note: List<Note>){
        notes.addAll(note)
    }
}