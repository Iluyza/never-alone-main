package ru.itis.core.di

import dagger.Module
import dagger.Provides
import ru.itis.core.dispathers.DispatchersProvider


@Module
class CoroutineModule {

    @Provides
    fun provideDispatchers(): DispatchersProvider {
        return DispatchersProvider()
    }

}
