package ru.itis.core.ui.destinations


sealed class MainDestinations(val route: String) {
    object UserFormDestination : MainDestinations("user_form")
    object MainScreenDestination : MainDestinations("main")
    object SettingsScreenDestination : MainDestinations("settings")
}
