package ru.itis.main_screen.home.models


sealed class HomeEvent {
    object EnterScreen : HomeEvent()
    object ReloadScreen : HomeEvent()
    data class OnAdvClick(val avd: Long) : HomeEvent()
}
