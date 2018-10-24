package com.binarynusantara.footballclubonline.utils

import kotlinx.coroutines.experimental.Unconfined
import kotlin.coroutines.experimental.CoroutineContext

class ContextProvider : CoroutineContextProvider() {
    override val main: CoroutineContext = Unconfined
}