package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.models.Task
import com.example.todoapp.screens.AddTaskScreen
import com.example.todoapp.screens.ComponentsScreen
import com.example.todoapp.screens.HomeScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MaterialTheme { TodoApp() } }
    }
}

@Composable
fun TodoApp() {
    val nav = rememberNavController()
    val tasks = remember { mutableStateListOf<Task>() }

    NavHost(navController = nav, startDestination = "home") {
        composable("home") {
            HomeScreen(
                tasks = tasks,
                onAdd = { nav.navigate("add") },
                onOpenComponents = { nav.navigate("components") }
            )
        }
        composable("add") { AddTaskScreen(tasks) { nav.popBackStack() } }
        composable("components") { ComponentsScreen { nav.popBackStack() } }
    }
}
