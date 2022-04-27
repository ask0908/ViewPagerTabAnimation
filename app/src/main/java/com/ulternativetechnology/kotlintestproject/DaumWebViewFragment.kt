package com.ulternativetechnology.kotlintestproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.fragment_daum_web_view.*

class DaumWebViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_daum_web_view, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val webSetting = daum_webview.settings
        webSetting.javaScriptEnabled = true
        webSetting.allowContentAccess = true
        webSetting.domStorageEnabled = true
        daum_webview.webViewClient = WebViewClient()
        daum_webview.loadUrl("https://www.daum.net")
    }
}