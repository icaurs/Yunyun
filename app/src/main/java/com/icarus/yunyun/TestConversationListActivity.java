package com.icarus.yunyun;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

/**
 * Created by DELL on 2015/6/4.
 */
public class TestConversationListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_conversation_list);

        initToolBar("会话列表");
        toolbar.setOnMenuItemClickListener(new ToolbarMenuItemClick());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new BackNavClick());

        ConversationListFragment listFragment = ConversationListFragment.getInstance();
        Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")
                .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")
                .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")
                .build();
        listFragment.initFragment(uri);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flFragment, listFragment);
        transaction.commit();
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
