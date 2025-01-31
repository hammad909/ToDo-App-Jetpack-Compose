package com.example.todo.todo.domain.repositiory

import com.example.todo.todo.domain.model.ToDoUI
import com.example.todo.todo.presentation.components.todoscreen.ToDoEvents
import kotlinx.coroutines.flow.Flow

interface ToDoRepository {

    suspend fun SaveToDO(toDoUI: ToDoUI)
    suspend fun GetToDO(): Flow<List<ToDoUI>>
    suspend fun DeleteToDo(id: String)

}