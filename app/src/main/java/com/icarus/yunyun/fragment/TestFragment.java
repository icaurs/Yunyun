package com.icarus.yunyun.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.icarus.yunyun.R;
import com.pnikosis.materialishprogress.ProgressWheel;

import io.rong.imkit.RongIM;

/**
 * Created by DELL on 2015/6/3.
 */
public class TestFragment extends BaseFragment {

    private Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test, null);

        btn = (Button) rootView.findViewById(R.id.btn);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = "00001"; //消息接收者的用户 Id。
                //启动二人会话。
                RongIM.getInstance().startPrivateChat(getActivity(), userId, "该聊天的标题");
            }
        });
    }
}
