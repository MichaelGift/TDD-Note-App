package com.myth.tdd_note_app.presentation.notes_list

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.myth.tdd_note_app.presentation.notes_list.components.MainAppBar
import com.myth.tdd_note_app.presentation.notes_list.components.NoteItem
import com.myth.tdd_note_app.presentation.notes_list.events.NoteEvent
import com.myth.tdd_note_app.presentation.notes_list.states.SearchWidgetState
import com.myth.tdd_note_app.presentation.util.Screen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    navController: NavController, viewModel: NotesListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val query = viewModel.query.value
    val searchWidgetState = viewModel.searchWidgetState.value
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.SaveEditScreen.route)
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Note")
            }
        },
        topBar = {
            MainAppBar(
                searchWidgetState = searchWidgetState,
                searchTextState = query.text,
                onTextChange = { viewModel.onEvent(NoteEvent.SearchNote(it)) },
                onCloseClicked = {
                    viewModel.onEvent(NoteEvent.SearchNote(""))
                    viewModel.onEvent(NoteEvent.ToggleSearchBar(SearchWidgetState.CLOSED))
                },
                onSearchClicked = { Log.d("Searched", it) },
                onSearchTriggered = {
                    viewModel.onEvent(NoteEvent.ToggleSearchBar(SearchWidgetState.OPENED))
                }
            )
        }
    ) { it ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn(
                state = rememberLazyListState(), modifier = Modifier.fillMaxSize()
            ) {
                items(items = state.notes.filter {
                    it.title.contains(query.text, ignoreCase = true) ||
                            it.content.contains(query.text, ignoreCase = true)
                }) { note ->

                    val dismissState = rememberDismissState(
                        confirmValueChange = {
                            if (it == DismissValue.DismissedToStart) {
                                viewModel.onEvent(NoteEvent.DeleteNote(note))
                                scope.launch {
                                    val result = snackBarHostState.showSnackbar(
                                        message = "Note deleted", actionLabel = "Undo"
                                    )
                                    if (result == SnackbarResult.ActionPerformed) viewModel.onEvent(
                                        NoteEvent.RestoreNote
                                    )
                                }
                            }
                            if (it == DismissValue.DismissedToEnd) {
                                navigateToNote(navController, note.id)
                            }
                            false
                        })

                    SwipeToDismiss(state = dismissState, background = {
                        val color = when (dismissState.dismissDirection) {
                            DismissDirection.EndToStart -> Color.Red
                            DismissDirection.StartToEnd -> Color.Green
                            else -> Color.Transparent
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(all = 4.dp)
                                .background(color)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(all = 16.dp)
                            )
                            Icon(
                                imageVector = Icons.Default.EditNote,
                                contentDescription = "Edit",
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(all = 16.dp)
                            )
                        }
                    }, dismissContent = {
                        NoteItem(note = note,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(all = 4.dp)
                                .clickable { navigateToNote(navController, note.id) })
                    })
                }
            }
        }
    }
}

fun navigateToNote(navController: NavController, id: Int?) {
    navController.navigate(
        Screen.SaveEditScreen.route + "?noteId=$id"
    )
}
