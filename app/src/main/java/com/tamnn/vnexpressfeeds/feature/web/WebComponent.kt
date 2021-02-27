package com.tamnn.vnexpressfeeds.feature.web

import com.tamnn.vnexpressfeeds.dependency.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [WebModule::class])
interface WebComponent {

    val presenter: WebContract.Presenter

    fun inject(activity: WebActivity)
}
