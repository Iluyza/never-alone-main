package ru.itis.main_screen.messenger.models

import ru.itis.main_screen.messenger.views.MessengerChatModel

sealed class MessengerViewState {
    object Loading : MessengerViewState()
    object Error : MessengerViewState()
    data class Display(
        val items: List<MessengerChatModel> = emptyList(),
        val isNetworkAvailable: Boolean = true
    ) : MessengerViewState()

    object NoChats : MessengerViewState()
    object NoInternet : MessengerViewState()
}
