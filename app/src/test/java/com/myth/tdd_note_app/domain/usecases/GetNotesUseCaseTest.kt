package com.myth.tdd_note_app.domain.usecases

import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.repository.NoteRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetNotesUseCaseTest {

    private val repository = mock(NoteRepository::class.java)
    private val getNotesUseCase = GetNotesUseCase(repository)

    @Test
    fun invoke_use_case_get_all_notes_from_repository() = runTest {
        val expectedList = flow {
            val notes = listOf(
                Note(1, "Title1", "Content1"),
                Note(2, "Title2", "Content2")
            )
            emit(notes)
        }
        `when`(getNotesUseCase()).thenReturn(expectedList)
        val result = getNotesUseCase()
        assertEquals(expectedList, result)
    }
}