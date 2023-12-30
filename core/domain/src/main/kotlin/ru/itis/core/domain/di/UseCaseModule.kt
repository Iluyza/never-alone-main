package ru.itis.core.domain.di

import dagger.Binds
import dagger.Module
import ru.itis.core.domain.usecase.*

@Module(includes = [UseCaseModuleBinds::class])
class UseCaseModule

@Module
internal interface UseCaseModuleBinds {

    @Binds
    fun provideSignInUseCase(useCase: SignInUseCase): ISignInUseCase

    @Binds
    fun providePhoneSignUpUseCase(useCasePhone: PhoneSignUpUseCase): IPhoneSignUpUseCase

    @Binds
    fun provideEmailSignUpUseCase(useCaseEmail: EmailSignUpUseCase): IEmailSignUpUseCase

    @Binds
    fun provideDatabaseUseCase(databaseUseCase: DatabaseUseCase): IDatabaseUseCase

    @Binds
    fun provideDatastoreUseCase(datastoreUseCase: DatastoreUseCase): IDatastoreUseCase

    @Binds
    fun provideMainUsersUseCase(mainUsersUseCase: MainUsersUseCase): IMainUsersUseCase
}
