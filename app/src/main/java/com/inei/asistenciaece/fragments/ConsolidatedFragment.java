package com.inei.asistenciaece.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.inei.asistenciaece.R;
import com.inei.asistenciaece.Utils.ConstantsUtils;

import org.apache.http.util.EncodingUtils;


public class ConsolidatedFragment extends Fragment {
    public static final String ARG_SECTION_TITLE = "section number";
    public static final String ARG_PASSWORD = "password";
    public static final String ARG_USERNAME = "user";
    private WebView webview;

    public ConsolidatedFragment() {
    }

    public static ConsolidatedFragment newInstance(String title, String password, String username) {
        ConsolidatedFragment fragment = new ConsolidatedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_TITLE, title);
        args.putString(ARG_PASSWORD, password);
        args.putString(ARG_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_consolidated, container, false);
        webview = (WebView) view.findViewById(R.id.webView);
        String postData = "user=" + ARG_USERNAME + "&password=" + ARG_PASSWORD;
        webview.postUrl(ConstantsUtils.URL_CONSOLIDATED, EncodingUtils.getBytes(postData, "BASE64"));
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webview.setWebChromeClient(new WebChromeClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        return view;
    }
}
