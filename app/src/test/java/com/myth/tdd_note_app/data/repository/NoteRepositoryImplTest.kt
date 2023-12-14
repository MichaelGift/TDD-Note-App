package com.myth.tdd_note_app.data.repository

import com.myth.tdd_note_app.data.data_source.NoteDao
import com.myth.tdd_note_app.domain.model.Note
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify

class NoteRepositoryImplTest {
    private val noteDao = mock(NoteDao::class.java)
    private val noteRepository = NoteRepositoryImpl(noteDao)

    @Test
    fun getAllNotes_emitListFromDao() = runTest {
        val expectedList = flow {
            val notes = listOf(
                Note(1, "Title1", "Content1"),
                Note(2, "Title2", "Content2")
            )
            emit(notes)
        }
        `when`(noteDao.getAllNotes()).thenReturn(expectedList)

        val result = noteRepository.getAllNotes()
        assertEquals(expectedList, result)
    }

    @Test
    fun getNoteById_getNoteFromDao() = runTest {
        val targetNote = Note(1, "Title", "Content")
        `when`(noteDao.getNoteById(1)).thenReturn(targetNote)

        val result = noteRepository.getNoteById(1)
        assertEquals(targetNote, result)
    }

    @Test
    fun insertNote() = runTest {
        val targetNote = Note(1, "Title", "Content")
        noteRepository.insertNote(targetNote)
        verify(noteDao).insertNote(targetNote)
    }

    @Test
    fun deleteNote() = runTest {
        val targetNote = Note(1, "Title", "Content")
        noteRepository.deleteNote(targetNote)
        verify(noteDao).deleteNote(targetNote)
    }
}