package ru.itis.core.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.itis.core.domain.models.User
import ru.itis.core.domain.viewstates.ResultState

interface DatabaseRepository {
    suspend fun addUser(user: User)
    suspend fun getUsers(): List<User>
    suspend fun updateUser(user: User)
    suspend fun getCurrentUserId(): String?
    suspend fun fetchCurrentUser()
    suspend fun checkEmail(email: String)

    val userFlowProcess: Flow<ResultState<User, Any>>
    val emailFlowProcess: Flow<ResultState<String, String>>
}
