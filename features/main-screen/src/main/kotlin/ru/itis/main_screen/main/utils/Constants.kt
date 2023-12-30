package ru.itis.main_screen.main.utils

import androidx.compose.runtime.Immutable
import ru.itis.main_screen.main.destinations.MainBottomScreen

@Immutable
internal object Constants {
    internal val MAIN_BOTTOM_SCREENS = listOf(
        MainBottomScreen.Home,
        MainBottomScreen.Messenger,
        MainBottomScreen.Profile
    )
}
