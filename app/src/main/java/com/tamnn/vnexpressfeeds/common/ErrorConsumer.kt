package com.tamnn.vnexpressfeeds.common

import androidx.annotation.CallSuper
import io.reactivex.functions.Consumer

class ErrorConsumer : Consumer<Throwable> {
    @CallSuper
    @Throws(Exception::class)
    override fun accept(throwable: Throwable) {
    }
}