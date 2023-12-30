package ru.itis.core.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.itis.core.domain.viewstates.ResultState

interface EmailSignUpRepository {
    val emailSignUpProcess: Flow<ResultState<String, String>>
    suspend fun createUserWithEmailAndPassword(email: String, password: String)
}
