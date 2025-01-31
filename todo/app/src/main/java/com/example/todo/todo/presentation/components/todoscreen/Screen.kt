package com.example.todo.todo.presentation.components.todoscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.todo.domain.model.ToDoUI
import com.example.todo.todo.presentation.components.component.ToDoItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.reflect.KSuspendFunction1

@Composable
fun Screen(
    state: ToDoState,
    events: KSuspendFunction1<ToDoEvents, Unit>

){
    val scope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    var showDialogDelete by remember { mutableStateOf(false) }
    var todoToDelete by remember { mutableStateOf<ToDoUI?>(null) }

    if (showDialog) {
        InputDialog(
            scope = scope,
            onDismiss = { showDialog = false },
            onConfirm = { todo ->
                scope.launch {
                    events(ToDoEvents.SaveToDo(todo))
                }
            }
        )
    }
    if (showDialogDelete && todoToDelete != null) {
        DeletionDialog(
            todo = todoToDelete!!,
            onDismiss = { showDialogDelete = false },
            onConfirm = {
                scope.launch {
                    events(ToDoEvents.DeleteToDo(todoToDelete!!.id))
                }
                showDialogDelete = false
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()){

        if(state.isLoading){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else if(state.todoList.isNotEmpty()){
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(state.todoList, key = {
                    it.id
                }){item ->
                    ToDoItem(toDoUI = item, onClick = {
                        showDialogDelete = true
                        todoToDelete = item
                    })
                }
            }
        }else{
             Text(text = "Activities to do?",
                 fontSize = 20.sp,
                 fontWeight = FontWeight.Bold,
                 modifier = Modifier.align(Alignment.Center)
                 )
        }

        FloatingActionButton(onClick = {
            showDialog = true

        },
            modifier = Modifier
                .size(110.dp)
                .padding(20.dp)
                .align(Alignment.BottomEnd)) {
            Icon(
                modifier = Modifier.size(35.dp),
                imageVector = Icons.Default.Add,
                contentDescription = "adding things"
            )
        }

    }

}

@Composable
fun InputDialog(scope: CoroutineScope,
                onDismiss: () -> Unit,
                onConfirm: (ToDoUI) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Enter Details") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = {newValue -> title = newValue },
                    label = { Text("Title") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    maxLines = 4
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {   scope.launch {
              onConfirm(ToDoUI(
                   id = "1",
                   title = title,
                   description = description,
                   date = "2025-02-15"
               ))
                    onDismiss()
            }},
                enabled = title.isNotBlank() && description.isNotBlank()
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


@Composable
fun DeletionDialog(
    todo: ToDoUI,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Delete ToDo Item") },
        text = {
            Text("Are you sure you want to delete '${todo.title}'?")
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm(todo.id)
            }) {
                Text("Delete")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}


