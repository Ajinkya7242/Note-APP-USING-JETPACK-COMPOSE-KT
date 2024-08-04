package com.example.notebookjetpackcompose.Screen

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notebookjetpackcompose.R
import com.example.notebookjetpackcompose.components.NoteButton
import com.example.notebookjetpackcompose.components.NoteInputText
import com.example.notebookjetpackcompose.data.NoteDataSource
import com.example.notebookjetpackcompose.model.Note
import com.example.notebookjetpackcompose.utils.formatDate
import java.time.format.DateTimeFormatter

@RequiresApi(value = 26)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit,
) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    val context= LocalContext.current
    Column(modifier = Modifier.padding(6.dp)) {
        TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }, actions = {
            Icon(imageVector = Icons.Default.Notifications, contentDescription = "Icon")
        },
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            )
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NoteInputText(
                modifier = Modifier
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                    .fillMaxWidth(), text = title, label = "Title", onTextChanged = {
                    if (it.all {
                            it.isLetter() || it.isWhitespace()
                        }) title = it
                }, imeAction = ImeAction.Done
            )

            NoteInputText(modifier = Modifier
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
                .fillMaxWidth(),
                text = description,
                label = "Add a Note",
                maxLine = 10,
                onTextChanged = {
                    if (it.all {
                            it.isLetter() || it.isWhitespace()
                        }) description = it
                })

            NoteButton(modifier = Modifier.padding(top = 20.dp), text = "Add a Note", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    onAddNote(Note(title=title, description =description))
                    Toast.makeText(context, "Note is Added ", Toast.LENGTH_SHORT).show()
                    title=""
                    description=""
                }
            })
        }
        LazyColumn {
            items(notes) {
                NoteRow(modifier = Modifier, notes =it ) {
                    onRemoveNote(it)
                }
            }
        }
    }
}


@RequiresApi(value = 26)
@Composable
fun NoteRow(
    modifier: Modifier,
    notes: Note,
    onNoteClicked : (Note) -> Unit){

    Surface(modifier= Modifier
        .padding(4.dp)
        .clip(RoundedCornerShape(topEnd = 28.dp, topStart = 28.dp))
        .fillMaxWidth(),
        color = Color(0xFfDfe6eb),
        tonalElevation = 6.dp
    ) {

        Column(modifier= Modifier
            .clickable {
                onNoteClicked(notes)
            }
            .padding(horizontal = 18.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.Start) {
            Text(text = notes.title,
                style = MaterialTheme.typography.labelLarge)
            Text(
                text = notes.description,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = formatDate(notes.entryData!!.time), fontSize = 12.sp, color = Color.DarkGray
            )

        }

    }
}

@RequiresApi(value = 26)
@Preview(showBackground = true)
@Composable
fun previewNoteScreen() {
    NoteScreen(notes = NoteDataSource().loadNote(), onAddNote = {}, onRemoveNote = {})
}