package com.myth.tdd_note_app.presentation.save_edit_note.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HintTextField(
    text: String,
    label: String,
    modifier: Modifier = Modifier,
    singleLine : Boolean = false,
    onValueChange : (String)->Unit
){
    OutlinedTextField(
        value = text,
        singleLine = singleLine,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(text=label) }
    )
}