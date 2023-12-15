package com.myth.tdd_note_app.domain.usecases

import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.repository.NoteRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetNoteByIdUseCaseTest {
    private val repository = mock<NoteRepository>()
    private val useCase = GetNoteByIdUseCase(repository)
    private val db = listOf(
        Note(1, "Title1", "Content1"),
        Note(2, "Title2", "Content2")
    )
    @Test
    fun get_note_by_id_from_repository() = runTest {
        `when`(useCase(1)).thenReturn(db[0])
        val result = useCase(1)
        assertEquals(db[0], result)
    }

    @Test
    fun return_null_if_note_id_does_not_exist() = runTest {
        `when`(useCase(3)).thenReturn(null)
        val result = useCase(3)
        assertEquals(null, result)
    }
}