package com.myth.tdd_note_app.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.myth.tdd_note_app.data.repository.FakeRepository
import com.myth.tdd_note_app.domain.model.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetNotesUseCaseTest {
    private lateinit var repository: FakeRepository
    private lateinit var useCase: GetNotesUseCase

    private val note1 = Note(1, "Title1", "Content1")
    private val note2 = Note(2, "Title2", "Content2")
    private val samples = listOf(note1, note2)

    @Before
    fun setup() {
        repository = FakeRepository()
        useCase = GetNotesUseCase(repository)

        repository.addSampleNotes(samples)
    }

    @Test
    fun `invoke gets all notes in repository`() = runTest {
        val allNotes = useCase().first()
        val notes = repository.getAllNotes().first()

        assertThat(allNotes).isEqualTo(notes)
    }
}