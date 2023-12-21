package com.myth.tdd_note_app.presentation.save_edit_note.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.myth.tdd_note_app.presentation.notes_list.components.HintTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    searchActive: Boolean,
    searchText: String,
    onValueChange: (String) -> Unit,
    onClickSearchIcon: () -> Unit
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            if (searchActive) {
                HintTextField(
                    text = searchText,
                    label  = "Search...",
                    onValueChange = onValueChange,
                    singleLine = true
                )
            } else Text(text = "Notes")
        },
        actions = {
            IconButton(onClick = onClickSearchIcon) {
                Icon(Icons.Filled.Search, contentDescription = "Localized description")
            }
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(Icons.Filled.Sort, contentDescription = "Localized description")
            }
        }
    )
}