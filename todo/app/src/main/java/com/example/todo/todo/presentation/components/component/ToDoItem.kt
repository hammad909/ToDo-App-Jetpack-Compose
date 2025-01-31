package com.example.todo.todo.presentation.components.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.todo.domain.model.ToDoUI

@Composable
fun ToDoItem(
    toDoUI: ToDoUI, onClick: () -> Unit
){

    Card (modifier = Modifier.fillMaxWidth()){
        Column(modifier = Modifier.padding(8.dp)
            .clickable(onClick = onClick) ) {
            //title
            Text(text = toDoUI.title,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
                )
            //description
           if(toDoUI.description != ""){
               Spacer(Modifier.height(10.dp))
               Text(text = toDoUI.description,
                   fontSize = 18.sp,
                   overflow = TextOverflow.Ellipsis
               )
           }
            Spacer(Modifier.height(12.dp))
            //date
            Text(modifier = Modifier.fillMaxWidth(),
                text = toDoUI.date,
                fontSize = 18.sp,
                textAlign = TextAlign.End
            )
        }
    }

}