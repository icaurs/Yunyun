package com.icarus.yunyun;

import android.os.Bundle;

/**
 * Created by DELL on 2015/6/3.
 */
public class ConversationListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversationlist); //加载会话列表页面 Fragment。
    }
}
