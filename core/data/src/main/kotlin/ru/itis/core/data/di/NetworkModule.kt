package ru.itis.core.data.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance().also {
            it.useAppLanguage()
        }
    }
}
