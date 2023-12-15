package com.myth.tdd_note_app.domain.usecases

import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.repository.NoteRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify

class DeleteNoteUseCaseTest {
    private val repository = mock(NoteRepository::class.java)
    private val useCase  = DeleteNoteUseCase(repository)
    private val note1 = Note(1, "Title1", "Content1")

    @Test
    fun confirm_repository_deletes_correct_note() = runTest {
        useCase(note1)
        verify(repository).deleteNote(note1)
    }
}