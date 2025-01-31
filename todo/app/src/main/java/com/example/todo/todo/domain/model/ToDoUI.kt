package com.example.todo.todo.domain.model

data class ToDoUI (
    val id: String,
    val title: String,
    val description : String = " ",
    val date : String
)

