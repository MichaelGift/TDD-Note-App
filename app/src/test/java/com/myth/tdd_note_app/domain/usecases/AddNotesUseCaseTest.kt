package com.myth.tdd_note_app.domain.usecases

import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.repository.NoteRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify

class AddNotesUseCaseTest {
    private val repository = mock(NoteRepository::class.java)
    private val useCase = AddNotesUseCase(repository)
    private val targetNote = Note(1, "Title", "Content")

    @Test
    fun add_note_to_repository() = runTest {
        useCase(targetNote)
        verify(repository).insertNote(targetNote)
    }

}