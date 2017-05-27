package com.laowojianada2802.ounews.module.video.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.laowojianada2802.ounews.R;
import com.laowojianada2802.ounews.annotation.ActivityFragmentInject;
import com.laowojianada2802.ounews.base.BaseActivity;
import com.laowojianada2802.ounews.base.BaseFragment;
import com.laowojianada2802.ounews.base.BaseFragmentAdapter;
import com.laowojianada2802.ounews.http.Api;
import com.laowojianada2802.ounews.module.video.presenter.IVideoPresenter;
import com.laowojianada2802.ounews.module.video.presenter.IVideoPresenterImpl;
import com.laowojianada2802.ounews.module.video.view.IVideoView;
import com.laowojianada2802.ounews.utils.ViewUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName: VideoActivity<p>
 * Author: oubowu<p>
 * Fuction: 视频界面<p>
 * CreateDate: 2016/2/23 21:31<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
@ActivityFragmentInject(contentViewId = R.layout.activity_video,
        menuId = R.menu.menu_video,
        hasNavigationView = true,
        toolbarTitle = R.string.video,
        toolbarIndicator = R.drawable.ic_list_white,
        menuDefaultCheckedItem = R.id.action_video)
public class VideoActivity extends BaseActivity<IVideoPresenter> implements IVideoView {

    @Override
    protected void initView() {
        mPresenter = new IVideoPresenterImpl(this);
    }

    @Override
    public void initViewPager() {

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        List<BaseFragment> fragments = new ArrayList<>();
        final List<String> title = Arrays.asList("热点", "娱乐", "搞笑", "精品");

        fragments.add(VideoListFragment.newInstance(Api.VIDEO_HOT_ID, 0));
        fragments.add(VideoListFragment.newInstance(Api.VIDEO_ENTERTAINMENT_ID, 1));
        fragments.add(VideoListFragment.newInstance(Api.VIDEO_FUN_ID, 2));
        fragments.add(VideoListFragment.newInstance(Api.VIDEO_CHOICE_ID, 3));

        BaseFragmentAdapter adapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragments, title);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        ViewUtil.dynamicSetTabLayoutMode(tabLayout);

        setOnTabSelectEvent(viewPager, tabLayout);

    }

}
