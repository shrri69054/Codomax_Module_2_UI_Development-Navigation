package com.example.todoapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.todoapp.R
import com.example.todoapp.utils.validateEmail
import com.example.todoapp.utils.validateName

/** Day 6 "component playground": buttons, inputs, list, card, image asset. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComponentsScreen(onBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var nameErr by remember { mutableStateOf<String?>(null) }
    var emailErr by remember { mutableStateOf<String?>(null) }
    var notify by remember { mutableStateOf(true) }
    var result by remember { mutableStateOf("") }
    val sample = listOf("Buy groceries", "Finish assignment", "Call mentor", "Read chapter 4")

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("UI Components") },
            navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Back") } }
        )
    }) { pad ->
        LazyColumn(
            Modifier.padding(pad),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Text("Buttons", style = MaterialTheme.typography.titleMedium) }
            item {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Button(onClick = {}) { Text("Filled") }
                    OutlinedButton(onClick = {}) { Text("Outlined") }
                    TextButton(onClick = {}) { Text("Text") }
                    IconButton(onClick = {}) { Icon(Icons.Default.Add, "Add") }
                }
            }

            item { Text("Form inputs + validation", style = MaterialTheme.typography.titleMedium) }
            item {
                OutlinedTextField(
                    value = name, onValueChange = { name = it; nameErr = null },
                    label = { Text("Name") }, isError = nameErr != null,
                    supportingText = { nameErr?.let { Text(it) } },
                    singleLine = true, modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                OutlinedTextField(
                    value = email, onValueChange = { email = it; emailErr = null },
                    label = { Text("Email") }, isError = emailErr != null,
                    supportingText = { emailErr?.let { Text(it) } },
                    singleLine = true, modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Notifications", Modifier.weight(1f))
                    Switch(checked = notify, onCheckedChange = { notify = it })
                }
            }
            item {
                Button(onClick = {
                    nameErr = validateName(name)
                    emailErr = validateEmail(email)
                    result = if (nameErr == null && emailErr == null) "Form is valid!" else ""
                }, modifier = Modifier.fillMaxWidth()) { Text("Validate") }
            }
            if (result.isNotEmpty()) item { Text(result, color = MaterialTheme.colorScheme.primary) }

            item { Text("Card with image asset", style = MaterialTheme.typography.titleMedium) }
            item {
                Card(Modifier.fillMaxWidth()) {
                    Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Image(painterResource(R.drawable.ic_empty_tasks), null, Modifier.size(64.dp))
                        Spacer(Modifier.width(16.dp))
                        Column {
                            Text("Stay organised", style = MaterialTheme.typography.titleMedium)
                            Text("Cards group related content.", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }

            item { Text("List view", style = MaterialTheme.typography.titleMedium) }
            items(sample) { s -> ListItem(headlineContent = { Text(s) }) }
        }
    }
}
