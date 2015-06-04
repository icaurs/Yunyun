package com.icarus.yunyun;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by DELL on 2015/6/3.
 */
public class ConversationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.conversation); //加载会话页面 Fragment。

        initToolBar("会话");
        toolbar.setOnMenuItemClickListener(new ToolbarMenuItemClick());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new BackNavClick());
//        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);
    }

    class ToolbarMenuItemClick implements Toolbar.OnMenuItemClickListener {

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()){

            }
            return true;
        }
    }
}
