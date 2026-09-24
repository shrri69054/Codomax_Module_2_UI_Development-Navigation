package com.example.todoapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.models.Category
import com.example.todoapp.models.Task
import com.example.todoapp.widgets.TaskCard
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    tasks: SnapshotStateList<Task>,
    onAdd: () -> Unit,
    onOpenComponents: () -> Unit
) {
    val snackbar = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var filter by remember { mutableStateOf<Category?>(null) }
    val visible = tasks.filter { filter == null || it.category == filter }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Tasks") },
                actions = { TextButton(onClick = onOpenComponents) { Text("UI Kit") } }
            )
        },
        snackbarHost = { SnackbarHost(snackbar) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) { Icon(Icons.Default.Add, "Add task") }
        }
    ) { pad ->
        Column(Modifier.padding(pad)) {
            // Category filter chips
            Row(
                Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilterChip(selected = filter == null, onClick = { filter = null }, label = { Text("All") })
                Category.values().take(3).forEach { c ->
                    FilterChip(selected = filter == c, onClick = { filter = c }, label = { Text(c.label) })
                }
            }

            if (visible.isEmpty()) {
                Column(
                    Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(painterResource(R.drawable.ic_empty_tasks), contentDescription = null, modifier = Modifier.size(120.dp))
                    Spacer(Modifier.height(12.dp))
                    Text("No tasks yet. Tap + to add one.")
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(visible, key = { it.id }) { task ->
                        TaskCard(
                            task = task,
                            onToggle = {
                                val i = tasks.indexOfFirst { it.id == task.id }
                                if (i >= 0) tasks[i] = task.copy(done = !task.done)
                            },
                            onDelete = {
                                val index = tasks.indexOf(task)
                                tasks.remove(task)
                                scope.launch {
                                    snackbar.currentSnackbarData?.dismiss()
                                    val r = snackbar.showSnackbar("Task deleted", actionLabel = "Undo")
                                    if (r == SnackbarResult.ActionPerformed) {
                                        tasks.add(index.coerceIn(0, tasks.size), task)
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}
