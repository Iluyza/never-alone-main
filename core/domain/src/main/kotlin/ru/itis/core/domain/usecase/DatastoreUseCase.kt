package ru.itis.core.domain.usecase

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.itis.core.domain.repository.DatastoreRepository
import javax.inject.Inject

interface IDatastoreUseCase {
    suspend fun saveOnBoardingState(completed: Boolean)
    suspend fun readOnBoardingState(): Flow<Boolean>
}

internal class DatastoreUseCase @Inject constructor(
    private val datastoreRepository: DatastoreRepository
) : IDatastoreUseCase {

    override suspend fun saveOnBoardingState(completed: Boolean) {
        Log.e("DEBUG", "OnBoarding saved")
        datastoreRepository.saveOnBoardingState(completed)
    }

    override suspend fun readOnBoardingState(): Flow<Boolean> {
        return datastoreRepository.readOnBoardingState.distinctUntilChanged()
    }
}
