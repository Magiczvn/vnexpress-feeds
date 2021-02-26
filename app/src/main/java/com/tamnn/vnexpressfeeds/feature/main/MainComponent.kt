package com.tamnn.vnexpressfeeds.feature.main

import com.tamnn.vnexpressfeeds.dependency.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent  {

    val presenter: MainContract.Presenter

    fun inject(activity: MainActivity)
}