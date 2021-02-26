package com.tamnn.vnexpressfeeds.feature.main

import android.app.Application
import com.tamnn.vnexpressfeeds.app.adapter.recyclerview.ItemsResult
import com.tamnn.vnexpressfeeds.domain.SchedulerFactory
import com.tamnn.vnexpressfeeds.domain.UseCaseFactory
import com.tamnn.vnexpressfeeds.mvp.BasePresenter

import dagger.Lazy
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable

class MainPresenter(private val _Application: Lazy<Application>,
                    private val _UseCaseFactory: Lazy<UseCaseFactory>,
                    private val _SchedulerFactory: Lazy<SchedulerFactory>)
    : BasePresenter<MainContract.View, MainViewState>(), MainContract.Presenter {

    private val _Items = ItemsResult()

    private var _GetRssFeedsDisposable: Disposable? = null

    //region Private
    private val _WorkerScheduler: Scheduler by lazy {
        _SchedulerFactory.get().single()
    }

     fun getRssFeeds() {
         _GetRssFeedsDisposable?.dispose()
         _GetRssFeedsDisposable = _UseCaseFactory.get().getRssFeed()
            .subscribeOn(_SchedulerFactory.get().io())
            .observeOn(_SchedulerFactory.get().main())
            .subscribe({

            }, {

            })
    }


    override fun onCreate(viewState: MainViewState) {
        super.onCreate(viewState)

    }

    override fun onAttachView(view: MainContract.View) {
        super.onAttachView(view)
        getRssFeeds()

    }

    override fun onDestroy() {
        super.onDestroy()
    }


}

