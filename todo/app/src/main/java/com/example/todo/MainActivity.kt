package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.todo.todo.presentation.components.ToDoViewModel
import com.example.todo.todo.presentation.components.todoscreen.Screen
import com.example.todo.ui.theme.TodoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("TODO")}
                        )
                    }
                ) { innerPadding ->
                    Box (modifier = Modifier.fillMaxSize().padding(innerPadding)){
                        val viewModel :ToDoViewModel by  viewModels()
                        val state by viewModel.state.collectAsState()
                       Screen(state,
                           events = viewModel::onEvent)
                                           }
                }
            }
        }
    }
}


