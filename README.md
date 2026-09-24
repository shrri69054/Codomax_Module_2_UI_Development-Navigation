# TodoApp2 📝

A mobile To-Do application built with **Kotlin** and **Jetpack Compose** for Android.
Created as part of Module 2 (Day 6 to Day 10): UI Development & Navigation.

## Features

- ✅ Add new tasks with a title and category
- 🗑️ Delete tasks, with an **Undo** option
- ☑️ Mark tasks as completed
- 🏷️ Categories: Work, Personal, Study
- 🔍 Filter the list by category
- ⚠️ Input validation with inline error messages:
  - empty title
  - fewer than 3 characters
  - more than 60 characters
  - duplicate task
- 🧭 Multi-screen navigation: Home, Add Task, UI Components
- 🎨 UI components showcase: buttons, text fields, switch, list view, cards, image asset

## Screenshots

| Home (empty) | Task list | Add task |
|:---:|:---:|:---:|
| ![Home empty](screenshots/home_empty.png) | ![Task list](screenshots/home_list.png) | ![Add task](screenshots/add_task.png) |

| Validation error | Delete + undo | UI components |
|:---:|:---:|:---:|
| ![Validation](screenshots/validation_error.png) | ![Undo](screenshots/delete_undo.png) | ![Components](screenshots/ui_components.png) |

## Tech Stack

- Kotlin
- Jetpack Compose (Material 3)
- Navigation Compose
- Android Studio

## Getting Started

1. Clone the repository
   ```bash
   git clone https://github.com/YOUR-USERNAME/TodoApp2.git
   ```
2. Open the project in **Android Studio**
3. Wait for the Gradle sync to finish
4. Select an emulator or connected device
5. Click **Run ▶**

## Project Structure

```
TodoApp2/
├── screenshots/
├── README.md
└── app/src/main/
    ├── java/com/example/todoapp/
    │   ├── MainActivity.kt        # Navigation and app state
    │   ├── models/
    │   │   └── Task.kt            # Task data class and Category enum
    │   ├── screens/
    │   │   ├── HomeScreen.kt      # Task list, filters, delete and undo
    │   │   ├── AddTaskScreen.kt   # Add task form with validation
    │   │   └── ComponentsScreen.kt# UI components playground
    │   ├── widgets/
    │   │   └── TaskCard.kt        # Reusable task card
    │   └── utils/
    │       └── Validators.kt      # Reusable validation functions
    └── res/drawable/
        └── ic_empty_tasks.xml     # Image asset
```

## Navigation

```
Home ──(+ button)──▶ Add Task
  │
  └──(UI Kit)──▶ UI Components
```

## What I Learned

- Building mobile UI with Compose components
- Managing state and recomposition
- Setting up multi-screen navigation
- Keeping validation logic in one reusable place
- Preparing a project for GitHub with a clear structure and README

## Future Improvements

- Save tasks locally so they survive app restarts (Room or DataStore)
- Edit existing tasks
- Due dates and reminders
- Dark mode toggle

## Author

**YOUR NAME**
[LinkedIn](https://www.linkedin.com/in/YOUR-PROFILE) · [GitHub](https://github.com/YOUR-USERNAME)
