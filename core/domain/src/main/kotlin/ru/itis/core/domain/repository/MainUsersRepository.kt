package ru.itis.core.domain.repository

import ru.itis.core.domain.models.User


interface MainUsersRepository {
    suspend fun getAllUsersByInterests(): List<User>
}
