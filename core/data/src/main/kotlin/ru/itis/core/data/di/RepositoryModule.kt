package ru.itis.core.data.di

import dagger.Binds
import dagger.Module
import ru.itis.core.data.repository.*
import ru.itis.core.domain.repository.*

@Module(includes = [RepositoryModuleBinds::class])
class RepositoryModule

@Module
internal interface RepositoryModuleBinds {

    @Binds
    fun provideSignInRepository(
        repository: SignInRepositoryImpl
    ): SignInRepository

    @Binds
    fun providePhoneSignUpRepository(
        repositoryImplPhone: PhoneSignUpRepositoryImpl
    ): PhoneSignUpRepository

    @Binds
    fun provideEmailSignUpRepository(
        emailSignUpRepositoryImpl: EmailSignUpRepositoryImpl
    ): EmailSignUpRepository

    @Binds
    fun provideDatabaseRepository(
        databaseRepositoryImpl: DatabaseRepositoryImpl
    ): DatabaseRepository

    @Binds
    fun provideDatastoreRepository(
        datastoreRepositoryImpl: DatastoreRepositoryImpl
    ): DatastoreRepository

    @Binds
    fun provideMainUsersRepository(
        mainUsersRepositoryImpl: MainUsersRepositoryImpl
    ): MainUsersRepository
}
