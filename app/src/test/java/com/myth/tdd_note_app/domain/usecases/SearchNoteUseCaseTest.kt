package com.myth.tdd_note_app.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.myth.tdd_note_app.data.repository.FakeRepository
import com.myth.tdd_note_app.domain.model.Note
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SearchNoteUseCaseTest {
    private lateinit var repository: FakeRepository
    private lateinit var useCase: SearchNoteUseCase

    private val note1 = Note(1, "Title1", "Content1")
    private val note2 = Note(2, "Title2", "Content2")
    private val samples = listOf(note1, note2)

    @Before
    fun setup(){
        repository = FakeRepository()
        useCase = SearchNoteUseCase(repository)

        repository.addSampleNotes(samples)
    }

    @Test
    fun `invoke searches for notes in repository`() = runTest{
        val target = useCase("1").first()
        assertThat(note1).isIn(target)
    }

    @Test
    fun `non existent search returns an empty list`() = runTest {
        val target =  useCase("search").first()
        assertThat(target).isEmpty()
    }
}