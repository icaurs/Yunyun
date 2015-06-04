package com.icarus.yunyun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.icarus.yunyun.R;
import com.icarus.yunyun.TestConversationListActivity;

import io.rong.imkit.RongIM;

/**
 * Created by DELL on 2015/6/3.
 */
public class TestFragment extends BaseFragment {

    private Button btn1, btn2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_test, null);

        btn1 = (Button) rootView.findViewById(R.id.btn1);
        btn2 = (Button) rootView.findViewById(R.id.btn2);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userId = "00001"; //消息接收者的用户 Id。
                //启动二人会话。
                RongIM.getInstance().startPrivateChat(getActivity(), userId, "该聊天的标题");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getActivity(), TestConversationListActivity.class);
                startActivity(intent);
            }
        });
    }
}
