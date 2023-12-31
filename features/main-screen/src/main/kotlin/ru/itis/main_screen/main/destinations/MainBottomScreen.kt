package ru.itis.main_screen.main.destinations

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector
import ru.itis.core.ui.R


@Stable
internal sealed class MainBottomScreen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) {
    object Home : MainBottomScreen("home", R.string.title_home, Icons.Outlined.Home)
    object Messenger : MainBottomScreen("messenger", R.string.title_messenger, Icons.Outlined.Menu)
    object Profile : MainBottomScreen("profile", R.string.title_profile, Icons.Outlined.Person)


}

internal fun String.asTitle(context: Context): String {
    return when (this) {
        MainBottomScreen.Home.route -> context.getString(MainBottomScreen.Home.resourceId)
        MainBottomScreen.Messenger.route -> context.getString(MainBottomScreen.Messenger.resourceId)
        MainBottomScreen.Profile.route -> context.getString(MainBottomScreen.Profile.resourceId)
        else -> ""
    }
}
