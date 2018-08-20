package com.silvozatechnologies.kitsuandroid.common.coroutine

import kotlin.coroutines.experimental.CoroutineContext

interface CoroutineContextProvider {
    fun uiContext() : CoroutineContext

    fun ioContext() : CoroutineContext
}