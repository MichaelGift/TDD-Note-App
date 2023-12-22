package com.myth.tdd_note_app.data.repository

import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepository : NoteRepository {
    /**A mutable list of [Note] objects, mimicking the database to store notes*/
    private val notes = mutableListOf<Note>()
    /**An integer variable simulating there auto-generated index for new notes*/
    private var currentId = 1

    /**
     * Returns a flow emitting the list of all notes in the database,
     * This provides data for use cases like displaying all notes in a list.
     */
    override fun getAllNotes(): Flow<List<Note>> = flow { emit(notes) }

    /**
     * Retrieves a specific note based on the provided ID.
     * Useful for use cases requiring details of a particular note.*/
    override suspend fun getNoteById(id: Int): Note? {
        return notes.firstOrNull { note -> note.id == id }
    }

    /**
     * Adds a new note to the repository, simulating the insertion into the database.
     * Generates an ID if not present*/
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

    /**
     * Removes a note from the repository, simulating the deletion from the database*/
    override suspend fun deleteNote(note: Note) {
        notes.remove(note)
    }

    /**
     * Searches for notes containing the specified query string in their title or content.
     * Supports use cases related to searching notes*/
    override fun searchNotes(query: String): Flow<List<Note>> = flow{
        val filteredNotes = notes.filter { note ->
            note.title.contains(query, ignoreCase = true) ||
                    note.content.contains(query, ignoreCase = true)
        }
        emit(filteredNotes)
    }

    /**
     * Populate the list of notes, typically used during test setup.
     * It assign auto-incremented IDs to notes without an ID*/
    fun addSampleNotes(noteList: List<Note>) {
        noteList.forEach { note ->
            if (note.id == null) {
                note.id = currentId++
            }
        }
        notes.addAll(noteList)
    }
}