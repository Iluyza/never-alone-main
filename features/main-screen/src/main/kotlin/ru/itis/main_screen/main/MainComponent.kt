package ru.itis.main_screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.BindsInstance
import dagger.Component
import ru.itis.main_screen.home.HomeDeps
import ru.itis.main_screen.messenger.MessengerDeps
import ru.itis.main_screen.profile.ProfileDeps

@Component(dependencies = [MainDeps::class])
internal interface MainComponent {

    val mainViewModel: MainViewModel.MainViewModelFactory

    @Component.Builder
    interface Builder {

        fun deps(mainDeps: MainDeps): Builder

        fun build(): MainComponent
    }
}

interface MainDeps : ProfileDeps, MessengerDeps, HomeDeps {

}

internal class MainComponentViewModel(deps: MainDeps) : ViewModel() {
    val mainComponent = DaggerMainComponent.builder().deps(deps).build()
}

internal class MainComponentViewModelFactory(
    private val deps: MainDeps
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainComponentViewModel(deps) as T
    }
}
