package ru.itis.core


interface EventHandler<T> {
    fun obtainEvent(event: T)
}
