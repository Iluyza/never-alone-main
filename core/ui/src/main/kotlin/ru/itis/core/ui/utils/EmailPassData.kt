package ru.itis.core.ui.utils

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class EmailPassData(
    val email: String
) : Parcelable
