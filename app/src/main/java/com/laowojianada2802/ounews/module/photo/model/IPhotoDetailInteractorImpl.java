package com.laowojianada2802.ounews.module.photo.model;

import com.laowojianada2802.ounews.base.BaseSubscriber;
import com.laowojianada2802.ounews.bean.SinaPhotoDetail;
import com.laowojianada2802.ounews.callback.RequestCallback;
import com.laowojianada2802.ounews.http.HostType;
import com.laowojianada2802.ounews.http.manager.RetrofitManager;

import rx.Subscription;

/**
 * ClassName: IPhotoDetailInteractorImpl<p>
 * Author: oubowu<p>
 * Fuction: 图片详情的Model层接口实现<p>
 * CreateDate: 2016/2/22 17:47<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public class IPhotoDetailInteractorImpl implements IPhotoDetailInteractor<SinaPhotoDetail> {
    @Override
    public Subscription requestPhotoDetail(final RequestCallback<SinaPhotoDetail> callback, String id) {
        return RetrofitManager.getInstance(HostType.SINA_NEWS_PHOTO).getSinaPhotoDetailObservable(id)
                .subscribe(new BaseSubscriber<>(callback));
    }
}
