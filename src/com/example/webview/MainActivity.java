package com.example.webview;

import java.net.URL;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	WebView myWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myWebView=(WebView)findViewById(R.id.webView1);
		myWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		myWebView.setWebViewClient(new MyWebViewClient());
		WebSettings webSettings=myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setLoadsImagesAutomatically(true);
	}
	private class MyWebViewClient extends WebViewClient{
		public boolean shouldOverrideUrlLoading(WebView view,String url){

			return false;
		}
	}
	public void load(View v){
		EditText myEditText=(EditText)findViewById(R.id.editText1);
		String url=myEditText.getText().toString();
		if(!url.contains("http://")){
			url="http://"+url;
		}
		try{
			new URL(url);
		}catch(Exception e){
			Toast.makeText(getApplicationContext(), "Invalid web address", Toast.LENGTH_SHORT).show();
			myEditText.setError("Invalid web address");
			return;
		}
		InputMethodManager imn=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imn.hideSoftInputFromWindow(myEditText.getWindowToken(),0);
		myWebView.loadUrl(url);
	}
	public void Backkey(View v){
		if(myWebView.canGoBack()){
			myWebView.goBack();
		}
		else
			finish();
	}
	public void Forward(View v){
		if(myWebView.canGoForward()){
			myWebView.goForward();
		}
	}
	public void Refresh(View v){
		//if(myWebView.canGoForward()){
			myWebView.reload();
			
		//}
	}
	public void Home(View v){
		myWebView.getSettings().setJavaScriptEnabled(true);
		myWebView.loadUrl("http://www.google.com");
	}
}
