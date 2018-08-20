package com.silvozatechnologies.kitsuandroid.common.coroutine

import kotlinx.coroutines.experimental.CommonPool
import kotlin.coroutines.experimental.CoroutineContext
import kotlinx.coroutines.experimental.android.UI

class AppCoroutineContextProvider : CoroutineContextProvider {
    override fun uiContext(): CoroutineContext {
        return UI
    }

    override fun ioContext(): CoroutineContext {
        return CommonPool
    }
}