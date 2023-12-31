package ru.itis.neveralone.di

import android.content.Context
import com.example.settings_screen.SettingsDeps
import dagger.BindsInstance
import dagger.Component
import ru.itis.core.data.di.DatabaseModule
import ru.itis.core.data.di.NetworkModule
import ru.itis.core.data.di.RepositoryModule
import ru.itis.core.di.CoroutineModule
import ru.itis.core.di.NetworkListenerModule
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.di.UseCaseModule
import ru.itis.core.domain.usecase.*
import ru.itis.core.network.NetworkListener
import ru.itis.features.signin.SignInDeps
import ru.itis.features.signup.SignUpDeps
import ru.itis.features.signup.email.create_user.CreateUserDeps
import ru.itis.features.signup.login_method.LoginMethodDeps
import ru.itis.features.signup.phone.verification.PhoneVerificationDeps
import ru.itis.features.splash.LoadingDeps
import ru.itis.main_screen.main.MainDeps
import ru.itis.onboarding.OnBoardingDeps
import ru.itis.user_form.UserFormDeps
import javax.inject.Singleton

@[Singleton Component(
    modules = [
        RepositoryModule::class,
        UseCaseModule::class,
        CoroutineModule::class,
        NetworkListenerModule::class,
        NetworkModule::class,
        DatabaseModule::class
    ]
)]
interface AppComponent :
    SignInDeps,
    SignUpDeps,
    PhoneVerificationDeps,
    CreateUserDeps,
    MainDeps,
    SettingsDeps,
    OnBoardingDeps,
    LoadingDeps,
    UserFormDeps,
    LoginMethodDeps {

    override val networkListener: NetworkListener
    override val sigInUseCase: ISignInUseCase
    override val singUpUseCase: IPhoneSignUpUseCase
    override val databaseUseCase: IDatabaseUseCase
    override val emailSignUpUseCase: IEmailSignUpUseCase
    override val dispatchersProvider: DispatchersProvider
    override val mainUsersUseCase: IMainUsersUseCase
    override val datastoreUseCase: IDatastoreUseCase

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Context): Builder

        fun build(): AppComponent
    }
}
