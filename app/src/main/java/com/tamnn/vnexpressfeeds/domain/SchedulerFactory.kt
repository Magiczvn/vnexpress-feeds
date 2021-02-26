package com.tamnn.vnexpressfeeds.domain

import io.reactivex.Scheduler

interface SchedulerFactory {

    fun io(): Scheduler

    fun computation(): Scheduler

    fun main(): Scheduler

    fun single(): Scheduler
}