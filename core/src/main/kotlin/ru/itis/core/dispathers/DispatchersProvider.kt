package ru.itis.core.dispathers

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext



open class DispatchersProvider {
    open val Main: CoroutineContext = Dispatchers.Main
    open val IO: CoroutineContext = Dispatchers.IO
    open val Default: CoroutineContext = Dispatchers.Default
}
