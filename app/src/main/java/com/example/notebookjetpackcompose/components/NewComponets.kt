package com.example.notebookjetpackcompose.components

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteInputText(modifier: Modifier=Modifier
                  ,text:String,
                  label:String,
                  maxLine:Int=3,
                  imeAction:ImeAction=ImeAction.Default,
                  onTextChanged:(String) -> Unit={},
                  onImeAction:()->Unit={},
                  ){

    val keyboardController= LocalSoftwareKeyboardController.current

    TextField(modifier = modifier.background(Color.White),
        value = text,
        onValueChange =onTextChanged,
        maxLines = maxLine,
        label = { Text(text = label)},
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone =  {
            onImeAction()
            keyboardController?.hide()
        }),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White

        )


    )
}


@Composable
fun NoteButton(modifier: Modifier=Modifier,
               text:String,
               onClick:()->Unit,
               enabled: Boolean =true
){
    Button(onClick=onClick,
        shape = CircleShape,
        enabled = enabled,
        modifier = modifier){
        Text(text)
    }

}

