package com.icarus.yunyun.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ObservableWebView;
import com.icarus.yunyun.R;

/**
 * Created by user on 2015-05-02.
 */
public class ViewPagerTab2WebViewFragment extends ObsBaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);

        final ObservableWebView webView = (ObservableWebView) view.findViewById(R.id.scroll);
        webView.loadUrl("file:///android_asset/lipsum.html");
        Activity parentActivity = getActivity();
        webView.setTouchInterceptionViewGroup((ViewGroup) parentActivity.findViewById(R.id.container));
        if (parentActivity instanceof ObservableScrollViewCallbacks) {
            webView.setScrollViewCallbacks((ObservableScrollViewCallbacks) parentActivity);
        }
        return view;
    }
}
