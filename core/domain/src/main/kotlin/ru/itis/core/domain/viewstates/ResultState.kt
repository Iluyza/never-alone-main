package ru.itis.core.domain.viewstates


sealed class ResultState<out T : Any, out E : Any> {
    object None : ResultState<Nothing, Nothing>()
    object InProcess : ResultState<Nothing, Nothing>()
    data class Error<E : Any>(val message: E?) : ResultState<Nothing, E>()
    data class Success<T : Any>(val data: T) : ResultState<T, Nothing>()
}
