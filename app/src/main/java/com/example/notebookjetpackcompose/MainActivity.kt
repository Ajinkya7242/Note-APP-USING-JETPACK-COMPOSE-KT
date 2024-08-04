package com.example.notebookjetpackcompose

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notebookjetpackcompose.Screen.NoteScreen
import com.example.notebookjetpackcompose.Screen.NoteViewModel
import com.example.notebookjetpackcompose.data.NoteDataSource
import com.example.notebookjetpackcompose.model.Note
import com.example.notebookjetpackcompose.ui.theme.NoteBookJetpackComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(value = 26)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            Surface(color=MaterialTheme.colorScheme.background) {
                val noteViewModel:NoteViewModel by viewModels()

                NoteApp(noteViewModel)
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteApp(noteViewModel:NoteViewModel= viewModel()){
    val notesList=noteViewModel.noteList.collectAsState().value
    NoteScreen( notes =notesList, onAddNote = {
        noteViewModel.addNotes(it)
    }, onRemoveNote = {
        noteViewModel.removeNotes(it)
    })
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NoteBookJetpackComposeTheme {
    }
}