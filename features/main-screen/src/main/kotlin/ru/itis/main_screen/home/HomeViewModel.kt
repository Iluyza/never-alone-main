package ru.itis.main_screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.itis.core.EventHandler
import ru.itis.core.dispathers.DispatchersProvider
import ru.itis.core.domain.usecase.IMainUsersUseCase
import ru.itis.core.network.NetworkListener
import ru.itis.main_screen.home.models.HomeAdvModel
import ru.itis.main_screen.home.models.HomeEvent
import ru.itis.main_screen.home.models.HomeViewState
import ru.itis.main_screen.home.models.fromUser
import javax.inject.Inject


internal class HomeViewModel(
    networkListener: NetworkListener,
    private val dispatchersProvider: DispatchersProvider,
    private val mainUsersUseCase: IMainUsersUseCase
) : ViewModel(), EventHandler<HomeEvent> {

    private val _homeViewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val homeViewState = _homeViewState.asStateFlow()

    init {
        networkListener.networkState
            .distinctUntilChanged()
            .onEach(this::onNetwork)
            .flowOn(dispatchersProvider.IO)
            .launchIn(viewModelScope)
    }

    private fun onNetwork(available: Boolean) {

    }

    override fun obtainEvent(event: HomeEvent) {
        when (val currentViewState = _homeViewState.value) {

            is HomeViewState.Loading -> {
                val data = mutableListOf(
                    HomeAdvModel(
                        age = 19,
                        name = "Kamilla",
                        city = "Kazan",
                        interests = listOf("Hard sex", "BDSM", "Spanking")
                    ),
                    HomeAdvModel(
                        age = 20,
                        name = "Kamilla2",
                        city = "Kazan",
                        interests = listOf("2 children", "Cooking", "Washing")
                    ),
                )
                _homeViewState.update {
                    HomeViewState.Display(items = data)
                }
            }
            is HomeViewState.Error -> {}
            is HomeViewState.NoInternet -> {}
            is HomeViewState.Display -> {
                reduce(event, currentViewState)
            }
        }

    }

    private fun reduce(event: HomeEvent, currentViewState: HomeViewState.Display) {
        when (event) {
            HomeEvent.EnterScreen -> {
                fetchMoreUsers()
            }
            else -> throw NotImplementedError("Unknown $event for $currentViewState")
        }
    }

    fun fetchMoreUsers() {
        viewModelScope.launch(dispatchersProvider.IO) {
            _homeViewState.update {
                HomeViewState.Loading
            }
            val users = mainUsersUseCase.getMatchedUsers()
            _homeViewState.update {
                HomeViewState.Display(
                    items = users.map {
                        it.fromUser()
                    }
                )
            }

        }
    }

    internal class HomeViewModelFactory @Inject constructor(
        private val networkListener: NetworkListener,
        private val dispatchersProvider: DispatchersProvider,
        private val mainUsersUseCase: IMainUsersUseCase
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(networkListener, dispatchersProvider, mainUsersUseCase) as T
        }
    }
}
