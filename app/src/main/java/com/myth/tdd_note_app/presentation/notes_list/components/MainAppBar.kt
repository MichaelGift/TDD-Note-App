package com.myth.tdd_note_app.presentation.notes_list.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import com.myth.tdd_note_app.presentation.notes_list.states.SearchWidgetState


@Composable
fun MainAppBar(
    searchWidgetState: SearchWidgetState,
    searchTextState: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit,
    onSearchTriggered: () -> Unit
) {
    when (searchWidgetState) {
        SearchWidgetState.CLOSED -> {
            CustomTopAppBar(
                onClickSearchIcon = onSearchTriggered
            )
        }

        SearchWidgetState.OPENED -> {
            AnimatedVisibility(
                visible = searchWidgetState == SearchWidgetState.OPENED
            ) {
                CustomSearchBar(
                    text = searchTextState,
                    onTextChange = onTextChange,
                    onCloseClicked = onCloseClicked,
                    onSearchClicked = onSearchClicked
                )
            }
        }
    }
}