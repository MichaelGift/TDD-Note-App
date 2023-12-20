package com.myth.tdd_note_app.presentation.save_edit_note

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myth.tdd_note_app.presentation.save_edit_note.components.HintTextField
import com.myth.tdd_note_app.presentation.save_edit_note.events.AddEditEvent
import com.myth.tdd_note_app.presentation.save_edit_note.events.UiEvent
import kotlinx.coroutines.flow.collectLatest


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveEditScreen(
    navController: NavController,
    viewModel: AddEditNoteViewModel = hiltViewModel()
) {
    val titleState = viewModel.noteTitle.value
    val contentState = viewModel.noteContent.value

    val snackBarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(message = event.message)
                }

                is UiEvent.SaveNote -> {
                    navController.navigateUp()
                }

                is UiEvent.DeleteNote -> {
                    snackBarHostState.showSnackbar(message = event.message)
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(AddEditEvent.SaveNote) },
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save Note")
            }
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            HintTextField(
                text = titleState.text,
                onValueChange = { viewModel.onEvent(AddEditEvent.EditNoteTitle(it)) },
                singleLine = true,
                label = "Title"
            )
            Spacer(modifier = Modifier.height(16.dp))
            HintTextField(
                text = contentState.text,
                onValueChange = { viewModel.onEvent(AddEditEvent.EditNoteContent(it)) },
                modifier = Modifier.fillMaxHeight(),
                singleLine = false,
                label = "Content"
            )
        }
    }
}