package com.myth.tdd_note_app.presentation.add_edit_note

import androidx.lifecycle.SavedStateHandle
import com.google.common.truth.Truth.assertThat
import com.myth.tdd_note_app.MainDispatcherRule
import com.myth.tdd_note_app.data.repository.FakeRepository
import com.myth.tdd_note_app.domain.model.Note
import com.myth.tdd_note_app.domain.usecases.AddNotesUseCase
import com.myth.tdd_note_app.domain.usecases.DeleteNoteUseCase
import com.myth.tdd_note_app.domain.usecases.GetNoteByIdUseCase
import com.myth.tdd_note_app.domain.usecases.GetNotesUseCase
import com.myth.tdd_note_app.domain.usecases.UseCases
import com.myth.tdd_note_app.presentation.add_edit_note.events.AddEditEvent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AddEditNoteViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val id = 1
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var repository: FakeRepository
    private lateinit var saveNote: AddNotesUseCase
    private lateinit var getNotes: GetNotesUseCase
    private lateinit var deleteNotes: DeleteNoteUseCase
    private lateinit var searchNoteId: GetNoteByIdUseCase
    private lateinit var useCase: UseCases
    private lateinit var viewModel: AddEditNoteViewModel


    private val note1 = Note(1, "Title1", "Content1")
    private val note2 = Note(2, "Title2", "Content2")
    private val samples = listOf(note1, note2)

    @Before
    fun setup() {
        savedStateHandle = SavedStateHandle(mapOf("noteId" to id))

        repository = FakeRepository()
        saveNote = AddNotesUseCase(repository)
        deleteNotes = DeleteNoteUseCase(repository)
        getNotes = GetNotesUseCase(repository)
        searchNoteId = GetNoteByIdUseCase(repository)

        useCase =
            UseCases(saveNote, deleteNotes, getNotes, searchNoteId)

        viewModel = AddEditNoteViewModel(useCase, SavedStateHandle(mapOf("noteId" to id)))

        repository.addSampleNotes(samples)
    }

    @Test
    fun `when editing a note currentNodeId is updated on init`() {
        assertThat(viewModel.currentNoteId).isEqualTo(id)
    }

    @Test
    fun `when editing a note noteContent and noteTitle are updated correctly`() = runTest {
        val id = 1

        val note = repository.getNoteById(id)
        val noteTitle = viewModel.noteTitle.value.text
        val noteContent = viewModel.noteContent.value.text

        assertThat(noteTitle).isEqualTo(note?.title)
        assertThat(noteContent).isEqualTo(note?.content)
    }

    @Test
    fun `noteTitle updates on EditNoteTitle event`() {
        val newTitle = "Updated Note Title"
        viewModel.onEvent(AddEditEvent.EditNoteTitle(newTitle))

        val noteTitle = viewModel.noteTitle.value.text

        assertThat(noteTitle).isEqualTo(newTitle)
    }


    @Test
    fun `noteContent updates on EditNoteContent event`() {
        val newContent = "Updated Note Content"
        viewModel.onEvent(AddEditEvent.EditNoteContent(newContent))

        val noteContent = viewModel.noteContent.value.text

        assertThat(noteContent).isEqualTo(newContent)
    }

    @Test
    fun `update note on SaveNote event if currentNoteId is not null`() = runTest {
        val newTitle = "Updated Note Title"
        viewModel.onEvent(AddEditEvent.EditNoteTitle(newTitle))

        val newContent = "Updated Note Content"
        viewModel.onEvent(AddEditEvent.EditNoteContent(newContent))

        viewModel.onEvent(AddEditEvent.SaveNote)
        val currentNoteId = viewModel.currentNoteId!!
        val newNote = Note(currentNoteId, newTitle, newContent)
//        saveNote(newNote)
        val notes = repository.getAllNotes().first()

        assertThat(notes).contains(newNote)
    }
}