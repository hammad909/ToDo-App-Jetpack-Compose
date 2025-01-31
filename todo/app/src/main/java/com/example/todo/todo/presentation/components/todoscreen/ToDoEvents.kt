package com.example.todo.todo.presentation.components.todoscreen

import com.example.todo.todo.domain.model.ToDoUI

sealed class ToDoEvents{

    data class SaveToDo(val toDoUI : ToDoUI) : ToDoEvents()
    data class DeleteToDo(val id : String) : ToDoEvents()

}