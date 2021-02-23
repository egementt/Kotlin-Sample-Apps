package com.tokgozdev.webviewgoogle

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.time.Duration

class MainActivity : AppCompatActivity() {

    private lateinit var myWebView: WebView
    val webViewClient = WebViewClient()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       myWebView = webView
       myWebView.webViewClient = webViewClient
       myWebView.loadUrl("https://www.google.com")

    }

     fun searchOnClick(view: View){

        val text = textField.editText?.text

        if( text.isNullOrEmpty()){
            val snackBar = Snackbar.make(view, "Please enter something.", Snackbar.LENGTH_LONG)
            snackBar.show()
        }else
            myWebView.loadUrl("https://www.google.com/search?q=${text}")
    }


}