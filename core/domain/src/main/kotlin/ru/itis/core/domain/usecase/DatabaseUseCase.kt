package ru.itis.core.domain.usecase

import dagger.Reusable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import ru.itis.core.domain.models.User
import ru.itis.core.domain.repository.DatabaseRepository
import ru.itis.core.domain.viewstates.ResultState
import javax.inject.Inject


interface IDatabaseUseCase {
    suspend fun addUser(user: User)
    suspend fun updateUser(user: User)
    suspend fun getUsers(): List<User>
    suspend fun getCurrentUserId(): String?
    suspend fun fetchCurrentUser()
    suspend fun checkEmail(email: String)

    val userFlow: Flow<ResultState<User, Any>>
    val emailFlow: Flow<ResultState<String, String>>
}

@Reusable
internal class DatabaseUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) : IDatabaseUseCase {

    override suspend fun addUser(user: User) {
        databaseRepository.addUser(user)
    }

    override suspend fun updateUser(user: User) {
        databaseRepository.updateUser(user)
    }

    override suspend fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentUserId(): String? {
        return databaseRepository.getCurrentUserId()
    }

    override suspend fun fetchCurrentUser() {
        databaseRepository.fetchCurrentUser()
    }

    override suspend fun checkEmail(email: String) {
        databaseRepository.checkEmail(email)
    }

    override val userFlow: Flow<ResultState<User, Any>>
        get() = databaseRepository.userFlowProcess.distinctUntilChanged()

    override val emailFlow: Flow<ResultState<String, String>>
        get() = databaseRepository.emailFlowProcess.distinctUntilChanged()

}
