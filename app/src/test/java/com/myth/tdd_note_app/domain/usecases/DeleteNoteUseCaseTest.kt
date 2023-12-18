package com.myth.tdd_note_app.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.myth.tdd_note_app.data.repository.FakeRepository
import com.myth.tdd_note_app.domain.model.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteNoteUseCaseTest {
    private lateinit var repository: FakeRepository
    private lateinit var useCase: DeleteNoteUseCase

    private val note1 = Note(1, "Title1", "Content1")
    private val note2 = Note(2, "Title2", "Content2")
    private val samples = listOf(note1, note2)

    @Before
    fun setUp() {
        repository = FakeRepository()
        useCase = DeleteNoteUseCase(repository)

        repository.addSampleNotes(samples)
    }

    @Test
    fun `invoking the use case deletes a note from the repository`() = runTest {
        useCase(note1)

        val notes = repository.getAllNotes().first()

        assertThat(notes).doesNotContain(note1)
    }
}