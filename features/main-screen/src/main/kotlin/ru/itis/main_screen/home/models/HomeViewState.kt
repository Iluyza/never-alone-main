package ru.itis.main_screen.home.models


sealed class HomeViewState {
    object Loading : HomeViewState()
    object Error : HomeViewState()
    data class Display(
        val items: List<HomeAdvModel> = emptyList(),
        val isNetworkAvailable: Boolean = true
    ) : HomeViewState()

    object NoInternet : HomeViewState()
}
