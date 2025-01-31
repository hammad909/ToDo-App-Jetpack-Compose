package com.example.todo.todo.domain.model.wrapper

import com.example.todo.todo.data.repository.data.ToDoDTO
import com.example.todo.todo.domain.model.ToDoUI
import java.util.UUID

fun ToDoUI.toToDoDTO(): ToDoDTO{
    return ToDoDTO(
        id = UUID.randomUUID().toString(),
        title = title,
        description = description,
        date = System.currentTimeMillis()
    )
}
