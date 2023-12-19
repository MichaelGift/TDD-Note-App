package com.myth.tdd_note_app.domain.usecases

import com.myth.tdd_note_app.data.repository.FakeRepository
import com.myth.tdd_note_app.domain.model.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class AddNotesUseCaseTest {
    private lateinit var repository: FakeRepository
    private lateinit var useCase: AddNotesUseCase

    private val note = Note(null,"Title", "Content")

    @Before
    fun setup() {
        repository = FakeRepository()
        useCase = AddNotesUseCase(repository)
    }

    @Test
    fun `invoking the use case adds a note to the repository`() = runTest{
        useCase(note)

        val notes = repository.getAllNotes().first()
        assertThat(notes).contains(note)
    }

    @Test
    fun `adding note with same id replaces old one`() = runTest {
        //Save old note
        useCase(note)

        // Edit and re save
        val updatedNote = Note(note.id, "New Title", "New Content")
        useCase(updatedNote)

        val notes = repository.getAllNotes().first()
        print(notes)
        assertThat(notes).contains(updatedNote)
        assertThat(notes).doesNotContain(note)
    }
}