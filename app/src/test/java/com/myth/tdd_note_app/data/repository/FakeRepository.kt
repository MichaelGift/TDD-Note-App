package com.myth.tdd_note_app.data.repository

import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : NoteRepository {
    private val notes = mutableListOf<Note>()
    private var currentId = 1

    override fun getAllNotes(): Flow<List<Note>> = flow { emit(notes) }

    override suspend fun getNoteById(id: Int): Note? {
        return notes.firstOrNull { note -> note.id == id }
    }

    override suspend fun insertNote(note: Note) {
        if (note.id == null) {
            note.id = currentId++
        }

        val existingNoteIndex = notes.indexOfFirst { it.id == note.id }

        if (existingNoteIndex != -1) {
            notes[existingNoteIndex] = note
        } else {
            notes.add(note)
        }
    }

    override suspend fun deleteNote(note: Note) {
        notes.remove(note)
    }

    fun addSampleNotes(noteList: List<Note>) {
        noteList.forEach { note ->
            if (note.id == null) {
                note.id = currentId++
            }
        }
        notes.addAll(noteList)
    }
}