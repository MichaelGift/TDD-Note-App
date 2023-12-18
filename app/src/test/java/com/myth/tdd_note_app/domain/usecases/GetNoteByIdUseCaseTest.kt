package com.myth.tdd_note_app.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.myth.tdd_note_app.data.repository.FakeRepository
import com.myth.tdd_note_app.domain.model.Note
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetNoteByIdUseCaseTest {
    private lateinit var repository: FakeRepository
    private lateinit var useCase: GetNoteByIdUseCase

    private val note1 = Note(1, "Title1", "Content1")
    private val samples = listOf(note1)

    @Before
    fun setUp() {
        repository = FakeRepository()
        useCase = GetNoteByIdUseCase(repository)

        repository.addSampleNotes(samples)
    }

    @Test
    fun `invoking the use case gets a note from the repository`() = runTest {
        val note  = useCase(1)

        assertThat(note).isEqualTo(note1)
    }

    @Test
    fun `return null if id does not exist`() = runTest {
        val note = useCase(2)

        assertThat(note).isEqualTo(null)
    }
}