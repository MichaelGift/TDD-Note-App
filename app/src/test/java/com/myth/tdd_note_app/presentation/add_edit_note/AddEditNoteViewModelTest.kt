package com.myth.tdd_note_app.presentation.add_edit_note

import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.myth.tdd_note_app.MainDispatcherRule
import com.myth.tdd_note_app.data.repository.FakeRepository
import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.usecases.AddNotesUseCase
import com.myth.tdd_note_app.domain.usecases.GetNoteByIdUseCase
import com.myth.tdd_note_app.domain.usecases.UseCases
import com.myth.tdd_note_app.presentation.add_edit_note.events.AddEditEvent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class AddEditNoteViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var repository: FakeRepository
    private lateinit var saveNote: AddNotesUseCase
    private lateinit var searchNoteId: GetNoteByIdUseCase
    private lateinit var useCases: UseCases
    private lateinit var viewModel: AddEditNoteViewModel

    private val note1 = Note(1, "Title1", "Content1")
    private val note2 = Note(2, "Title2", "Content2")
    private val samples = listOf(note1, note2)

    @Before
    fun setup() {
        repository = FakeRepository()
        saveNote = AddNotesUseCase(repository)
        searchNoteId = GetNoteByIdUseCase(repository)
        useCases =
            UseCases(saveNote, mock(), mock(), searchNoteId)
        repository.addSampleNotes(samples)
    }

    @Test
    fun `when editing a note currentNodeId is updated on init`() {
        val id = 1
        savedStateHandle = SavedStateHandle(mapOf("noteId" to id))
        viewModel = AddEditNoteViewModel(useCases, savedStateHandle)
        assertThat(viewModel.currentNoteId).isEqualTo(id)
    }

    @Test
    fun `when editing a note noteContent and noteTitle are updated correctly`() = runTest {
        val id = 1
        viewModel = AddEditNoteViewModel(useCases, SavedStateHandle(mapOf("noteId" to id)))

        val note = repository.getNoteById(id)
        val noteTitle = viewModel.noteTitle.value.text
        val noteContent = viewModel.noteContent.value.text

        assertThat(noteTitle).isEqualTo(note?.title)
        assertThat(noteContent).isEqualTo(note?.content)
    }

    @Test
    fun `noteTitle updates on EditNoteTitle event`() {
        viewModel = AddEditNoteViewModel(useCases, SavedStateHandle())

        val newTitle = "Updated Note Title"
        viewModel.onEvent(AddEditEvent.EditNoteTitle(newTitle))

        val noteTitle = viewModel.noteTitle.value.text

        assertThat(noteTitle).isEqualTo(newTitle)
    }


    @Test
    fun `noteContent updates on EditNoteContent event`() {
        viewModel = AddEditNoteViewModel(useCases, SavedStateHandle())

        val newContent = "Updated Note Content"
        viewModel.onEvent(AddEditEvent.EditNoteContent(newContent))

        val noteContent = viewModel.noteContent.value.text

        assertThat(noteContent).isEqualTo(newContent)
    }

    @Test
    fun `update note on SaveNote event if currentNoteId is not null`() = runTest {
        val id = 1
        viewModel = AddEditNoteViewModel(useCases, SavedStateHandle(mapOf("noteId" to id)))

        val newTitle = "Updated Note Title"
        viewModel.onEvent(AddEditEvent.EditNoteTitle(newTitle))

        val newContent = "Updated Note Content"
        viewModel.onEvent(AddEditEvent.EditNoteContent(newContent))

        viewModel.onEvent(AddEditEvent.SaveNote)

        val currentNoteId = viewModel.currentNoteId!!
        val newNote = Note(currentNoteId, newTitle, newContent)
        val notes = repository.getAllNotes().first()

        assertThat(notes).contains(newNote)
    }


}