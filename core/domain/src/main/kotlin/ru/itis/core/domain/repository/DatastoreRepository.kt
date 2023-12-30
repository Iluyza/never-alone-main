package ru.itis.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface DatastoreRepository {
    suspend fun saveOnBoardingState(completed: Boolean)
    val readOnBoardingState: Flow<Boolean>
}
