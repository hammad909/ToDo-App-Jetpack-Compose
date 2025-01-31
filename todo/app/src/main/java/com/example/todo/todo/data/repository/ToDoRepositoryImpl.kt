package com.example.todo.todo.data.repository

import android.annotation.SuppressLint
import com.example.todo.todo.data.repository.data.ToDoDTO
import com.example.todo.todo.domain.model.ToDoUI
import com.example.todo.todo.domain.model.wrapper.toToDoDTO
import com.example.todo.todo.domain.model.wrapper.toToDoUI
import com.example.todo.todo.domain.repositiory.ToDoRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ToDoRepositoryImpl : ToDoRepository {

    private val firebase = FirebaseDatabase.getInstance()
    private val todoRef = firebase.getReference("TodoList")

    @SuppressLint("SuspiciousIndentation")
    override suspend fun SaveToDO(toDoUI: ToDoUI) {
      val toDoDTO = toDoUI.toToDoDTO()
        try {
             todoRef.child(toDoDTO.id!!).setValue(toDoDTO).await()

        }catch (_: Exception){

        }
    }

    override suspend fun GetToDO(): Flow<List<ToDoUI>> = callbackFlow{

        val listener = object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val toDoUIItems : List<ToDoUI> = snapshot.children.mapNotNull {
                    it.getValue(ToDoDTO::class.java)
                }.map {
                    it.toToDoUI()
                }

                trySend(toDoUIItems)
            }

            override fun onCancelled(error: DatabaseError) {
                close()
            }
        }

        todoRef.addValueEventListener(
            listener
        )

        awaitClose {
            todoRef.removeEventListener(listener)
        }
    }


    override suspend fun DeleteToDo(id: String) {
        try {
            val todoRef = firebase.getReference("TodoList").child(id)
            todoRef.removeValue().await()
            println("Deleted item with ID: $id")
        } catch (e: Exception) {
            println("Error deleting item: ${e.message}")
        }
    }

}