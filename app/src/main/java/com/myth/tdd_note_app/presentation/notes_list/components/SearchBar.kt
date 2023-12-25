package com.myth.tdd_note_app.presentation.notes_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CustomSearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.primary
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(8.dp)),
            value = text,
            onValueChange = { onTextChange(it) },
            placeholder = { Text(text = "Search...") },
            singleLine = true,
            leadingIcon = {
                IconButton(onClick = onCloseClicked) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Icon")
                }
            },
            trailingIcon = {
                IconButton(onClick = {
                    if (text.isNotEmpty()) onTextChange("")
                    else onCloseClicked()
                }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close Icon")
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onDone = { onSearchClicked(text) }),
        )
    }
}

@Composable
@Preview
fun PreviewSearchBar() {
    CustomSearchBar(
        text = "Random Text",
        onTextChange = {},
        onCloseClicked = {},
        onSearchClicked = {}
    )
}