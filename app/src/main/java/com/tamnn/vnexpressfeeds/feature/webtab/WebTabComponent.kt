package com.tamnn.vnexpressfeeds.feature.webtab
import com.tamnn.vnexpressfeeds.dependency.ActivityScope

import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [WebTabModule::class])
interface WebTabComponent {

    val presenter: WebTabContract.Presenter

    fun inject(fragment: WebTabFragment)
}
