package com.laowojianada2802.ounews.module.photo.presenter;

import com.laowojianada2802.ounews.base.BasePresenterImpl;
import com.laowojianada2802.ounews.module.photo.view.IPhotoView;

/**
 * ClassName: IPhotoPresenterImpl<p>
 * Author: oubowu<p>
 * Fuction: 图片代理接口实现<p>
 * CreateDate: 2016/2/21 3:46<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public class IPhotoPresenterImpl extends BasePresenterImpl<IPhotoView, Void> implements IPhotoPresenter{

    public IPhotoPresenterImpl(IPhotoView view) {
        super(view);
        view.initViewPager();
    }

}
