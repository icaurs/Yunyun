package com.icarus.yunyun.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.icarus.yunyun.R;

/**
 * Created by user on 2015-05-02.
 */
public class UserIntroFragment extends ObsBaseFragment {

    private ObservableScrollView scrollView;

    private TextView tvNickName, tvSign;

    private FrameLayout flSign;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_user_intro, container, false);

        scrollView = (ObservableScrollView) rootView.findViewById(R.id.scroll);
        scrollView.setTouchInterceptionViewGroup((ViewGroup) getActivity().findViewById(R.id.container));
        if (getActivity() instanceof ObservableScrollViewCallbacks) {
            scrollView.setScrollViewCallbacks((ObservableScrollViewCallbacks) getActivity());
        }

        tvNickName = (TextView) rootView.findViewById(R.id.tvNickName);
        tvSign = (TextView) rootView.findViewById(R.id.tvSign);
        flSign = (FrameLayout) rootView.findViewById(R.id.flSign);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvNickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        flSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
