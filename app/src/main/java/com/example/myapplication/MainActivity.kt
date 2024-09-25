package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import data.Task
import data.TaskController
import fruit.FruitViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val a=FruitViewModel()

        val controller=TaskController()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                /*Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                }*/
                //FruitListScreen(a)
                TaskListScreen(controller, this)
            }
        }
    }
}



@Composable
fun TaskListScreen(controller: TaskController, context: Context) {
    var newTaskName by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        for (task in controller.tasks) {
            TaskItem(task, controller, context)
        }

        TextField(
            modifier = Modifier.padding(5.dp).fillMaxWidth(),
            value = newTaskName,
            onValueChange = { newTaskName = it },
            label = { Text("Task Name") }
        )

        Button(onClick = {
            if (newTaskName.isNotEmpty()) {
                controller.addTask(newTaskName)
                newTaskName = ""
            }
        }) {
            Text("Add New Task")
        }
    }
}

fun openSecondActivity(context: Context, task: Task) {
    val intent = Intent(context, MainActivity2::class.java).apply {
        putExtra("taskId", task.id)
        putExtra("taskName", task.name)
    }
    context.startActivity(intent)
}

@Composable
fun TaskItem(task: Task, controller: TaskController, context: Context) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(text = task.name, modifier = Modifier.weight(1f))
        Button(onClick = {
            controller.removeTask(task.id)
        }) {
            Text("Remove")
        }
        Button(onClick = {
            openSecondActivity(context, task)  // Abre la segunda actividad con la tarea
        }) {
            Text("Open")
        }
    }
}


///--------------------------------------fr
@Composable
fun FruitListScreen(viewModel:FruitViewModel){
    Column (modifier = Modifier.padding(16.dp)){
        for(fruit in viewModel.fruits){
            Text(text = fruit.name)
        }

        Button(onClick = {viewModel.addFruit("Orange")}) {
            Text("ADD ORANGE")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val vi=FruitViewModel()
    MyApplicationTheme {
        FruitListScreen(vi)
    }
}