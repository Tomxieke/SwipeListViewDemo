package com.example.ciist.swipelistviewdemo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.example.ciist.swipelistviewdemo.swipelistview.BaseSwipeListViewListener;
import com.example.ciist.swipelistviewdemo.swipelistview.SwipeListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected static final String TAG = "test";
    private SwipeListView mSwipeListView;
    private DataAdapter mAdapter;
    private List<String> mDatas;

    /**
     * 将dp单位数值转化为px单位数值
     * @param context
     * @param pxValue
     * @return
     */
    public  int dp2px(Context context, float pxValue){
        Resources resource = context.getResources();
         float scale = resource.getDisplayMetrics().density;
        return (int) (pxValue * scale + 0.5f);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initDatas();

        //获取屏幕宽度，单位为px
        WindowManager windowManager = getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        int screenWidth = display.getWidth();


        mSwipeListView = (SwipeListView) findViewById(R.id.id_swipelistview);
        mSwipeListView.setOffsetLeft(screenWidth-dp2px(this,60));

        mAdapter = new DataAdapter(this, mDatas , mSwipeListView);
        mSwipeListView.setAdapter(mAdapter);


        mSwipeListView.setSwipeListViewListener(new BaseSwipeListViewListener()
        {
            @Override
            public void onChoiceChanged(int position, boolean selected)
            {
                Log.d(TAG, "onChoiceChanged:" + position + ", " + selected);
            }

            @Override
            public void onChoiceEnded()
            {
                Log.d(TAG, "onChoiceEnded");
            }

            @Override
            public void onChoiceStarted()
            {
                Log.d(TAG, "onChoiceStarted");
            }

            @Override
            public void onClickBackView(int position)
            {
                Log.d(TAG, "onClickBackView:" + position);
            }

            @Override
            public void onClickFrontView(int position)
            {
                Log.d(TAG, "onClickFrontView:" + position);
                startActivity(new Intent(MainActivity.this,NextActivity.class));
            }

            @Override
            public void onClosed(int position, boolean fromRight)
            {
                Log.d(TAG, "onClosed:" + position + "," + fromRight);
            }

            @Override
            public void onDismiss(int[] reverseSortedPositions)
            {
                Log.d(TAG, "onDismiss");

            }

            @Override
            public void onFirstListItem()
            {
                Log.d(TAG, "onFirstListItem");
            }

            @Override
            public void onLastListItem()
            {
                Log.d(TAG, "onLastListItem");
            }

            @Override
            public void onListChanged()
            {
                Log.d(TAG, "onListChanged");

                mSwipeListView.closeOpenedItems();

            }

            @Override
            public void onMove(int position, float x)
            {
                Log.d(TAG, "onMove:" + position + "," + x);
            }

            @Override
            public void onOpened(int position, boolean toRight)
            {
                Log.d(TAG, "onOpened:" + position + "," + toRight);
            }

            @Override
            public void onStartClose(int position, boolean right)
            {
                Log.d(TAG, "onStartClose:" + position + "," + right);
            }

            @Override
            public void onStartOpen(int position, int action, boolean right)
            {
                Log.d(TAG, "onStartOpen:" + position + "," + action + ","
                        + right);
            }
        });
    }

    private void initDatas()
    {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i <= 'Z'; i++)
            mDatas.add((char) i + "");
    }
}
