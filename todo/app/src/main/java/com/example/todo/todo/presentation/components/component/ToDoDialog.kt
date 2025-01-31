package com.example.todo.todo.presentation.components.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@Composable
fun ToDoDialog(onDismiss : () -> Unit,
               isEditMode : Boolean = false,
               onAddToDo: (String, String)->Unit){

    androidx.compose.ui.window.Dialog(
        onDismissRequest = { onDismiss() },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
        Surface(shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(16.dp)) {
            var currentTitle by rememberSaveable { mutableStateOf("") }
            var currentDescription by rememberSaveable { mutableStateOf("") }

            OutlinedTextField(
                value = currentTitle,
                onValueChange = { newValue -> currentTitle = newValue },
                label = { Text("Enter Title") },
                placeholder = { Text("Type here...") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp)
                )
            OutlinedTextField(
                value = currentDescription,
                onValueChange = { newValue -> currentDescription = newValue },
                label = { Text("Enter Description") },
                placeholder = { Text("Type here...") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
            )

        }

    }

}