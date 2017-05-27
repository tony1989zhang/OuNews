package com.laowojianada2802.ounews.module.news.view;

import com.laowojianada2802.ounews.base.BaseView;
import com.laowojianada2802.ounews.greendao.NewsChannelTable;

import java.util.List;

/**
 * ClassName: INewsChannelView<p>
 * Author: oubowu<p>
 * Fuction: 新闻频道管理视图接口<p>
 * CreateDate: 2016/2/19 22:53<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface INewsChannelView extends BaseView {

    void initTwoRecyclerView(List<NewsChannelTable> selectChannels, List<NewsChannelTable> unSelectChannels);

}
