package com.application.habit_tracker_app.presentation.navigation

enum class TaskScreens {
    ListScreen,
    DetailScreen;
    companion object {
        fun fromRoute(route: String?): TaskScreens =
            when (route?.substringBefore("/")) {
                ListScreen.name -> ListScreen
                DetailScreen.name -> DetailScreen
                null -> ListScreen
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}