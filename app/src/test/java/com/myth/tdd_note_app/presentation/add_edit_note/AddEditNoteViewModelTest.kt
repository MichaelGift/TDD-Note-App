package com.myth.tdd_note_app.presentation.add_edit_note

import androidx.lifecycle.SavedStateHandle
import com.myth.tdd_note_app.MainDispatcherRule
import com.myth.tdd_note_app.domain.usecases.UseCases
import com.myth.tdd_note_app.presentation.add_edit_note.events.AddEditEvent
import com.myth.tdd_note_app.presentation.add_edit_note.events.UiEvent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class AddEditNoteViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val savedStateHandle = SavedStateHandle(mapOf("noteId" to 1))
    private lateinit var useCase: UseCases
    private lateinit var viewModel: AddEditNoteViewModel

    @Before
    fun setUp() {
        useCase = UseCases(mock(), mock(), mock(), mock())
        viewModel = AddEditNoteViewModel(useCase, savedStateHandle)
    }

    @Test
    fun viewModelInitialization_withNoteId_loadsNote() = runTest {
        assertEquals(1, viewModel.currentNoteId)
    }

    @Test
    fun onEvent_EditNoteTitle_EditsNoteTitles() {
        viewModel.onEvent(AddEditEvent.EditNoteTitle("New Title"))
        assertEquals("New Title", viewModel.noteTitle.value.text)
    }

    @Test
    fun onEvent_EditNoteContent_EditsNoteContent(){
        viewModel.onEvent(AddEditEvent.EditNoteContent("New Content"))
        assertEquals("New Content", viewModel.noteContent.value.text)
    }

    @Test
    fun onEvent_SaveNote_triggersSaveNoteEvent() = runTest {
        viewModel.onEvent(AddEditEvent.SaveNote)
        val event =  viewModel.eventFlow.first()
        assertTrue(event is  UiEvent.SaveNote)
    }
}