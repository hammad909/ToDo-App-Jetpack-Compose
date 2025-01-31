package com.example.todo.todo.presentation.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.todo.data.repository.ToDoRepositoryImpl
import com.example.todo.todo.domain.model.ToDoUI
import com.example.todo.todo.domain.repositiory.ToDoRepository
import com.example.todo.todo.presentation.components.todoscreen.ToDoEvents
import com.example.todo.todo.presentation.components.todoscreen.ToDoState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ToDoViewModel: ViewModel() {

    private val repository : ToDoRepository = ToDoRepositoryImpl()
    private val _state = MutableStateFlow(ToDoState())
    val state = _state.asStateFlow()

    init{
        GetToDO()
    }

    suspend fun onEvent(events :ToDoEvents){
        when(events){
            is ToDoEvents.SaveToDo -> {
                SaveToDo(events.toDoUI)
            }
            is ToDoEvents.DeleteToDo -> {
                repository.DeleteToDo(events.id)
                GetToDO()
            }
        }
    }


    private fun SaveToDo(toDoUI : ToDoUI){

        viewModelScope.launch {
            repository.SaveToDO(toDoUI)
        }
    }

    private fun GetToDO(){
        viewModelScope.launch {
            repository.GetToDO().collect{
                toDoList->
                _state.value = state.value.copy(
                    todoList = toDoList,
                    isLoading = false
                )
            }
        }
    }

}