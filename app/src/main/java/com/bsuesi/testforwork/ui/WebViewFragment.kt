package com.bsuesi.testforwork.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.bsuesi.testforwork.R
import com.bsuesi.testforwork.databinding.FragmentMainBinding
import com.bsuesi.testforwork.databinding.FragmentWebViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : Fragment(R.layout.fragment_web_view) {
    private var _binding: FragmentWebViewBinding? = null
    private val binding: FragmentWebViewBinding get() = _binding!!
    val args: WebViewFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWebViewBinding.bind(view)
        initWebView()

    }
    private fun initWebView(){
        with(binding.webView){
            with(settings) {
                allowContentAccess = true
                javaScriptCanOpenWindowsAutomatically = true
                domStorageEnabled = true
                allowContentAccess = true
                allowFileAccess = true
            }

            webChromeClient = WebChromeClient()
            webViewClient = WebViewClient()

            loadUrl(args.myArg)
            setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_BACK

                    && event.action == MotionEvent.ACTION_UP
                    && binding.webView.canGoBack()
                ) {
                    binding.webView.goBack()
                    return@OnKeyListener true
                }
                false
            })
        }
    }
    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }

}