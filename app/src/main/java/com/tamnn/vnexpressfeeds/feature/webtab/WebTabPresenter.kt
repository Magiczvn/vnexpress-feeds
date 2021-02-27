package com.tamnn.vnexpressfeeds.feature.webtab


import com.tamnn.vnexpressfeeds.domain.SchedulerFactory
import com.tamnn.vnexpressfeeds.domain.UseCaseFactory
import com.tamnn.vnexpressfeeds.mvp.BasePresenter
import dagger.Lazy
import io.reactivex.Scheduler



class WebTabPresenter(private val _UseCaseFactory: Lazy<UseCaseFactory>,
                      private val _SchedulerFactory: Lazy<SchedulerFactory>)
    : BasePresenter<WebTabContract.View, WebTabViewState>(), WebTabContract.Presenter {

    //region Private
    private val _WorkerScheduler: Scheduler by lazy {
        _SchedulerFactory.get().single()
    }

    //endregion Private

    //region BasePresenter
    override fun onAttachView(view: WebTabContract.View) {
        super.onAttachView(view)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
    //endregion BasePresenter

    //region WebTabContract.Presenter
}