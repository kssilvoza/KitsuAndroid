package com.silvozatechnologies.kitsuandroid.common.coroutine

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class TestCoroutineContextProvider : CoroutineContextProvider {
    override fun uiContext(): CoroutineContext {
        return Unconfined
    }

    override fun ioContext(): CoroutineContext {
        return Unconfined
    }
}