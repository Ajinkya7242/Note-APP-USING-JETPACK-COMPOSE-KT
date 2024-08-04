package com.example.notebookjetpackcompose.Screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notebookjetpackcompose.data.NoteDataSource
import com.example.notebookjetpackcompose.model.Note
import com.example.notebookjetpackcompose.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class NoteViewModel @Inject constructor(private val repository: NoteRepository):ViewModel() {

    val _noteList= MutableStateFlow<List<Note>>(emptyList())
     val noteList=_noteList.asStateFlow()


    init {
       viewModelScope.launch(Dispatchers.IO) {
           repository.getAllNotes().distinctUntilChanged().collect{listOfNotes->
               if(listOfNotes.isNotEmpty()){
                   _noteList.value=listOfNotes
               }
           }
       }
    }
     fun addNotes(note:Note) = viewModelScope.launch { repository.addNote(note) }

     fun removeNotes(note:Note)=viewModelScope.launch { repository.deleteNote(note) }

     fun updateNote(note:Note)=viewModelScope.launch { repository.updateNote(note) }


}