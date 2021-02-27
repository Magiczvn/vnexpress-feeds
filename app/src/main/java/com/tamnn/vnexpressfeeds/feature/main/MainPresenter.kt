package com.tamnn.vnexpressfeeds.feature.main

import android.app.Application
import com.tamnn.vnexpressfeeds.app.adapter.recyclerview.ItemsResult
import com.tamnn.vnexpressfeeds.common.ErrorConsumer
import com.tamnn.vnexpressfeeds.domain.SchedulerFactory
import com.tamnn.vnexpressfeeds.domain.UseCaseFactory
import com.tamnn.vnexpressfeeds.mvp.BasePresenter

import dagger.Lazy
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import java.util.concurrent.Callable

class MainPresenter(private val _Application: Lazy<Application>,
                    private val _UseCaseFactory: Lazy<UseCaseFactory>,
                    private val _SchedulerFactory: Lazy<SchedulerFactory>,
                    private val _ItemBuilder: Lazy<ArticleItemBuilder>)
    : BasePresenter<MainContract.View, MainViewState>(), MainContract.Presenter {

    private val _Items = ItemsResult()

    private var _GetRssFeedsDisposable: Disposable? = null
    private var _ShowRssFeedsDisposable: Disposable? = null

    override var currentPosition
        get() = mViewState.currentPosition
        set(value) {
            mViewState.currentPosition = value
        }

    override var topOffset: Int
        get() = mViewState.topOffset
        set(value) {
            mViewState.topOffset = value
        }

    override fun refreshRssFeeds() {
        topOffset = 0
        currentPosition = 0
        getRssFeeds(true)
    }

    //region Private
    private val _WorkerScheduler: Scheduler by lazy {
        _SchedulerFactory.get().single()
    }

     private fun getRssFeeds(force: Boolean) {
         if(!force && mViewState.channel != null) {
             showRssFeedAsync()
             return
         }

         _GetRssFeedsDisposable?.dispose()
         _GetRssFeedsDisposable = _UseCaseFactory.get().getRssFeed()
            .subscribeOn(_SchedulerFactory.get().io())
            .observeOn(_SchedulerFactory.get().main())
            .subscribe({
                mViewState.channel = it
                showRssFeedAsync()
            }, {

            })
    }

    private fun showRssFeedAsync() {
        val channel = mViewState.channel?: return

        val work = Callable<Unit> {
            val newItems = _ItemBuilder.get().showRssFeeds(channel)
            mViewState.items = newItems
            _Items.pushItems(newItems)
        }

        _ShowRssFeedsDisposable?.dispose()
        _ShowRssFeedsDisposable = _UseCaseFactory.get().doWork(work)
            .subscribeOn(_WorkerScheduler)
            .observeOn(_SchedulerFactory.get().main())
            .subscribe(Consumer {
                showItemResult()
            }, ErrorConsumer())
    }

    private fun showItemResult() {
        val items = _Items.popItems()

        items?.let { mView?.showItems(it) }
    }


    override fun onCreate(viewState: MainViewState) {
        super.onCreate(viewState)
    }

    override fun onAttachView(view: MainContract.View) {
        super.onAttachView(view)
        getRssFeeds(false)
    }

    override fun onDestroy() {
        _GetRssFeedsDisposable?.dispose()
        _ShowRssFeedsDisposable?.dispose()
        super.onDestroy()
    }


}

