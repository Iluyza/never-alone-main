package ru.itis.core.common

infix fun String.buildPath(endPoint: String): String {
    return this.plus("/${endPoint}")
}
