package com.myth.tdd_note_app.domain.model

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class NoteTest {
    private lateinit var note : Note
    @Before
    fun create_sample_note(){
        note = Note("Title", "Content")
    }

    @Test
    fun create_note_with_title_and_content() {
        assertEquals("Title", note.title)
        assertEquals("Content", note.content)
    }
}