package com.example.notebookjetpackcompose.repository

import com.example.notebookjetpackcompose.data.NoteDatabase
import com.example.notebookjetpackcompose.model.Note
import dagger.hilt.internal.aggregatedroot.codegen._com_example_notebookjetpackcompose_NoteApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabase: NoteDatabase) {

    suspend fun addNote(note: Note)=noteDatabase.noteDao().insert(note)

    suspend fun updateNote(note: Note)=noteDatabase.noteDao().updateNote(note)

    suspend fun deleteNote(note:Note)=noteDatabase.noteDao().deleteNote(note)

    suspend fun deleteAllNotes()=noteDatabase.noteDao().deleteAll()
    suspend fun getAllNotes(): Flow<List<Note>> = noteDatabase.noteDao().getNotes().flowOn(Dispatchers.IO).conflate()
}