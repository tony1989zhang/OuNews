package com.laowojianada2802.ounews.module.news.view;

import com.laowojianada2802.ounews.base.BaseView;
import com.laowojianada2802.ounews.bean.NeteastNewsDetail;

/**
 * ClassName: INewsDetailView<p>
 * Author: oubowu<p>
 * Fuction: 新闻详情视图接口<p>
 * CreateDate: 2016/2/19 14:52<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface INewsDetailView extends BaseView{

    void initNewsDetail(NeteastNewsDetail data);

}
