package com.example.todo.todo.presentation.components.todoscreen

import com.example.todo.todo.domain.model.ToDoUI

data class ToDoState(
    val isLoading : Boolean  = true,
    val todoList : List<ToDoUI> = emptyList()
)
