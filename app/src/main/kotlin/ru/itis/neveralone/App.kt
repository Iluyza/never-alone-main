package ru.itis.neveralone

import android.app.Application
import ru.itis.neveralone.di.AppComponent
import ru.itis.neveralone.di.DaggerAppComponent


class App : Application() {
    val appComponent: AppComponent by lazy(LazyThreadSafetyMode.NONE) {
        DaggerAppComponent.builder()
            .application(application = this.applicationContext)
            .build()
    }
}
