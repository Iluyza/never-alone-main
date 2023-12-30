package ru.itis.main_screen.messenger.models


sealed class MessengerEvent {
    object EnterScreen : MessengerEvent()
    object ReloadScreen : MessengerEvent()
    data class OnChatClick(val chat: Long) : MessengerEvent()
}
