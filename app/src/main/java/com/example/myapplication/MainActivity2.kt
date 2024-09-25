package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Recibir los datos del Intent
        val taskId = intent.getIntExtra("taskId", -1) // -1 es el valor por defecto si no se encuentra
        val taskName = intent.getStringExtra("taskName") ?: "No Name"

        // Usar Jetpack Compose para establecer el contenido
        setContent {
            MyApplicationTheme {
                TaskDetailScreen(taskId, taskName)
            }
        }
    }
}

@Composable
fun TaskDetailScreen(taskId: Int, taskName: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Task ID: $taskId")
        Text(text = "Task Name: $taskName")
    }
}
