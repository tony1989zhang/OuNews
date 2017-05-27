package com.laowojianada2802.ounews.module.photo.view;

import com.laowojianada2802.ounews.base.BaseView;
import com.laowojianada2802.ounews.bean.SinaPhotoDetail;

/**
 * ClassName: IPhotoDetailView<p>
 * Author: oubowu<p>
 * Fuction: 图片新闻详情视图接口<p>
 * CreateDate: 2016/2/21 1:35<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface IPhotoDetailView extends BaseView {

    void initViewPager(SinaPhotoDetail photoList);

}
