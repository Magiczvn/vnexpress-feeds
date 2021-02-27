package com.tamnn.vnexpressfeeds.feature.web

import com.tamnn.vnexpressfeeds.domain.SchedulerFactory
import com.tamnn.vnexpressfeeds.domain.UseCaseFactory
import com.tamnn.vnexpressfeeds.mvp.BasePresenter
import dagger.Lazy
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function

class WebPresenter(private val _UseCaseFactory: Lazy<UseCaseFactory>,
                   private val _SchedulerFactory: Lazy<SchedulerFactory>)
    : BasePresenter<WebContract.View, WebViewState>(), WebContract.Presenter {

    //region Private
    private val _WorkerScheduler: Scheduler by lazy {
        _SchedulerFactory.get().single()
    }

    //endregion Private

    //region BasePresenter
    override fun onAttachView(view: WebContract.View) {
        super.onAttachView(view)

    }

    override fun onDestroy() {
        super.onDestroy()

    }
    //endregion BasePresenter

}