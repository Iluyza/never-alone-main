package ru.itis.main_screen.messenger.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itis.core.ui.theme.AppTheme

@Composable
internal fun MessengerViewNoChats() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.backgroundPrimary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "No chats")
    }
}

@Preview
@Composable
private fun MessengerViewNoChatsPreview() = MessengerViewNoChats()
