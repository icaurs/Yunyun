package com.icarus.yunyun;

import android.os.Bundle;

/**
 * Created by DELL on 2015/6/3.
 */
public class ConversationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation); //加载会话页面 Fragment。
    }
}
