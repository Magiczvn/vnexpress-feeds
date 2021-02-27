package com.tamnn.vnexpressfeeds.feature.webtab

import android.content.Context
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import com.tamnn.vnexpressfeeds.MyApplication
import com.tamnn.vnexpressfeeds.R
import com.tamnn.vnexpressfeeds.common.RxBus
import com.tamnn.vnexpressfeeds.dependency.HasComponent
import com.tamnn.vnexpressfeeds.domain.SchedulerFactory
import com.tamnn.vnexpressfeeds.mvp.BaseMvpFragment
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.webtab_fragment.*
import javax.inject.Inject


class WebTabFragment : BaseMvpFragment<WebTabContract.View, WebTabContract.Presenter, WebTabViewState, WebTabScreen>(),
        HasComponent<WebTabComponent>, WebTabContract.View {

    companion object {

        fun instance(screen: WebTabScreen): WebTabFragment {
            val fragment = WebTabFragment()
            fragment.setScreen(screen)
            return fragment
        }
    }

    //region Dependency
    @Inject
    lateinit var _SchedulerFactory: SchedulerFactory

    @Inject
    lateinit var _Bus: RxBus
    //endregion Dependency

    //region Private
    private var _Disposable: CompositeDisposable? = null
    var _WebChromeClient: WebChromeClient? = null
    //endregion Private

    //region MVP
    override fun onCreatePresenter(context: Context?) = component.presenter

    override fun onCreateViewState(context: Context?) = WebTabViewState()

    override val viewStateTag: String get() = WebTabViewState::class.java.name

    override val layoutResource get() = R.layout.webtab_fragment
    //endregion MVP

    //region HasComponent
    override val component by lazy {
        MyApplication.get(context!!).component.plus(WebTabModule())
    }
    //endregion HasComponent

    //region Lifecycle
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        component.inject(this)

        val s = webview_wv?.settings
        s?.builtInZoomControls = false
        s?.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        s?.useWideViewPort = true
        s?.loadWithOverviewMode = true
        s?.domStorageEnabled = true
        s?.databaseEnabled = true
        s?.allowFileAccess = true

        webview_wv?.webViewClient = WebViewClient()
        webview_wv?.webChromeClient = WebChromeClient()

        _Disposable = CompositeDisposable(

        )

        webtab_srl?.setOnRefreshListener {
            webview_wv?.reload()
        }

        loadWeb()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _Disposable?.dispose()

        super.onDestroyView()
    }

    override fun onResume() {
        super.onResume()

        webview_wv?.onResume()
    }

    override fun onPause() {
        super.onPause()

        webview_wv?.onPause()
    }
    //endregion Lifecycle

    //region Helper
    private fun loadWeb() {
        val url = screen.url

        webview_wv?.loadUrl(url)
    }

}