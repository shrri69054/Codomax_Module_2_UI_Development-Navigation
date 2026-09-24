package com.example.todoapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.todoapp.models.Category
import com.example.todoapp.models.Task
import com.example.todoapp.utils.validateTitle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(tasks: SnapshotStateList<Task>, onBack: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf(Category.PERSONAL) }
    var error by remember { mutableStateOf<String?>(null) }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Add Task") },
            navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Back") } }
        )
    }) { pad ->
        Column(
            Modifier.padding(pad).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it; error = null },
                label = { Text("Task title") },
                isError = error != null,
                supportingText = { error?.let { Text(it) } },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Text("Category", style = MaterialTheme.typography.labelLarge)
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Category.values().take(3).forEach { c ->
                    FilterChip(selected = category == c, onClick = { category = c }, label = { Text(c.label) })
                }
            }
            Button(
                onClick = {
                    val err = validateTitle(title, tasks)
                    if (err != null) error = err
                    else {
                        tasks.add(Task(System.currentTimeMillis(), title.trim(), category))
                        onBack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Save Task") }
        }
    }
}
