package com.myth.tdd_note_app.presentation.notes_list

import com.google.common.truth.Truth.assertThat
import com.myth.tdd_note_app.MainDispatcherRule
import com.myth.tdd_note_app.data.repository.FakeRepository
import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.usecases.AddNotesUseCase
import com.myth.tdd_note_app.domain.usecases.DeleteNoteUseCase
import com.myth.tdd_note_app.domain.usecases.GetNoteByIdUseCase
import com.myth.tdd_note_app.domain.usecases.GetNotesUseCase
import com.myth.tdd_note_app.domain.usecases.UseCases
import com.myth.tdd_note_app.presentation.notes_list.events.NoteEvent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NotesListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var repository: FakeRepository
    private lateinit var addNotes: AddNotesUseCase
    private lateinit var getNotes: GetNotesUseCase
    private lateinit var deleteNotes: DeleteNoteUseCase
    private lateinit var getNoteById: GetNoteByIdUseCase
    private lateinit var useCase: UseCases
    private lateinit var viewModel: NotesListViewModel

    private val note1 = Note(1, "Title1", "Content1")
    private val note2 = Note(2, "Title2", "Content2")
    private val samples = listOf(note1, note2)

    @Before
    fun setUp() {
        repository = FakeRepository()

        addNotes = AddNotesUseCase(repository)
        deleteNotes = DeleteNoteUseCase(repository)
        getNotes = GetNotesUseCase(repository)
        getNoteById = GetNoteByIdUseCase(repository)

        useCase =
            UseCases(addNotes, deleteNotes, getNotes, getNoteById)
        viewModel = NotesListViewModel(useCase)

        repository.addSampleNotes(samples)
    }

    @Test
    fun `gets all notes on init`() = runTest {
        val notes = viewModel.state.value.notes
        val targetNotes = repository.getAllNotes().first()

        assertThat(notes).isEqualTo(targetNotes)
    }

    @Test
    fun `deletes target note`() = runTest {
        viewModel.onEvent(NoteEvent.DeleteNote(note1))
        val notes = repository.getAllNotes().first()

        assertThat(note1).isNotIn(notes)
    }

    @Test
    fun `restores target note`() = runTest {
        viewModel.onEvent(NoteEvent.DeleteNote(note1))
        viewModel.onEvent(NoteEvent.RestoreNote)
        val notes = repository.getAllNotes().first()

        assertThat(notes).contains(note1)
    }

    @Test
    fun `onEvent search search for a note`()= runTest{
        viewModel.onEvent(NoteEvent.SearchNote("Title1"))
        val notes  = viewModel.state.value.notes
        assertThat(notes).contains(note1)
    }

    @Test
    fun `non existent search returns an empty list` (){
        viewModel.onEvent(NoteEvent.SearchNote("Title3"))
        val notes  = viewModel.state.value.notes
        assertThat(notes).isEmpty()
    }

}