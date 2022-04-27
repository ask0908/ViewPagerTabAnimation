package com.ulternativetechnology.kotlintestproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_daum_web_view.*
import kotlinx.android.synthetic.main.fragment_naver_web_view.*

class NaverWebViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_naver_web_view, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webSetting = naver_webview.settings
        webSetting.javaScriptEnabled = true
        webSetting.allowContentAccess = true
        webSetting.domStorageEnabled = true
        naver_webview.webViewClient = WebViewClient()
        naver_webview.loadUrl("https://www.naver.com")
    }

}