package com.example.notebookjetpackcompose.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.notebookjetpackcompose.model.Note

class NoteDataSource{
    @RequiresApi(Build.VERSION_CODES.O)
    fun loadNote() : List<Note>{
        val notes = listOf(
            Note(
                title = "Grocery Shopping",
                description = "Buy milk, bread, and eggs."
            ),
            Note(
                title = "Meeting with Bob",
                description = "Discuss the project timeline and deliverables."
            ),
            Note(
                title = "Workout Routine",
                description = "Gym at 7 AM, cardio and strength training."
            ),
            Note(
                title = "Read Book",
                description = "Finish reading 'Atomic Habits' by James Clear."
            ),
            Note(
                title = "Weekly Review",
                description = "Review tasks and goals for the week."
            ),
            Note(
                title = "Call Mom",
                description = "Catch up with Mom and update her on recent events."
            ),
            Note(
                title = "Fix Bug #123",
                description = "Resolve the issue with the login functionality."
            ),
            Note(
                title = "Plan Vacation",
                description = "Research destinations and book flights for the summer vacation."
            ),
            Note(
                title = "Write Blog Post",
                description = "Draft a blog post about Kotlin vs Java for Android development."
            ),
            Note(
                title = "Team Lunch",
                description = "Organize a team lunch for Friday."
            )
        )
        return notes
    }
}