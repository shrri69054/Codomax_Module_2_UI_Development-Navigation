package com.example.todoapp.models

enum class Category(val label: String) {
    WORK("Work"), PERSONAL("Personal"), STUDY("Study"), SHOPPING("Shopping")
}

data class Task(
    val id: Long,
    val title: String,
    val category: Category,
    val done: Boolean = false
)
