package com.example.todoapp.utils

import com.example.todoapp.models.Task

/** Returns an error message, or null if the title is valid. */
fun validateTitle(raw: String, existing: List<Task>): String? {
    val t = raw.trim()
    return when {
        t.isEmpty() -> "Title cannot be empty"
        t.length < 3 -> "Title must be at least 3 characters"
        t.length > 60 -> "Title is too long (max 60)"
        existing.any { it.title.equals(t, ignoreCase = true) } -> "This task already exists"
        else -> null
    }
}

fun validateEmail(raw: String): String? {
    val t = raw.trim()
    return when {
        t.isEmpty() -> "Email cannot be empty"
        !android.util.Patterns.EMAIL_ADDRESS.matcher(t).matches() -> "Enter a valid email"
        else -> null
    }
}

fun validateName(raw: String): String? = when {
    raw.trim().isEmpty() -> "Name cannot be empty"
    raw.trim().length < 2 -> "Name is too short"
    raw.any { it.isDigit() } -> "Name cannot contain digits"
    else -> null
}
