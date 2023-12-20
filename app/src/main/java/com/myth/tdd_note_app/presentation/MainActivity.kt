package com.myth.tdd_note_app.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.myth.tdd_note_app.presentation.save_edit_note.SaveEditScreen
import com.myth.tdd_note_app.presentation.notes_list.NoteListScreen
import com.myth.tdd_note_app.presentation.util.Screen
import com.myth.tdd_note_app.ui.theme.TDDNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TDDNoteAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController =  rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NotesScreen.route
                    ){
                        composable(route = Screen.NotesScreen.route){
                            NoteListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.SaveEditScreen.route +
                            "?noteId={noteId}",
                            arguments = listOf(
                                navArgument(name = "noteId"){
                                    type= NavType.IntType
                                    defaultValue = -1
                                },
                            )
                        ){
                            SaveEditScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}