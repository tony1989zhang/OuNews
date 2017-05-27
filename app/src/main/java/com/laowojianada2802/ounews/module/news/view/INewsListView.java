package com.laowojianada2802.ounews.module.news.view;

import android.support.annotation.NonNull;

import com.laowojianada2802.ounews.base.BaseView;
import com.laowojianada2802.ounews.bean.NeteastNewsSummary;
import com.laowojianada2802.ounews.common.DataLoadType;

import java.util.List;

/**
 * ClassName: INewsListView<p>
 * Author: oubowu<p>
 * Fuction: 新闻列表视图接口<p>
 * CreateDate: 2016/2/18 14:42<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface INewsListView extends BaseView {

    void updateNewsList(List<NeteastNewsSummary> data, @NonNull String errorMsg, @DataLoadType.DataLoadTypeChecker int type);

}
