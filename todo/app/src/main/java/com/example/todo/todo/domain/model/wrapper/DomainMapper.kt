package com.example.todo.todo.domain.model.wrapper

import com.example.todo.todo.data.repository.data.ToDoDTO
import com.example.todo.todo.domain.model.ToDoUI
import com.example.todo.todo.presentation.components.component.FormatToReadableDate

fun ToDoDTO.toToDoUI() : ToDoUI {
    return ToDoUI(
        id = id!!,
        title = title,
        description = description,
        date = FormatToReadableDate(date!!),
    )
}