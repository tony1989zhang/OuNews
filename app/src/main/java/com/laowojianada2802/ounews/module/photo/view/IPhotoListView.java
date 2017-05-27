package com.laowojianada2802.ounews.module.photo.view;

import android.support.annotation.NonNull;

import com.laowojianada2802.ounews.base.BaseView;
import com.laowojianada2802.ounews.bean.SinaPhotoList;
import com.laowojianada2802.ounews.common.DataLoadType;

import java.util.List;

/**
 * ClassName: IPhotoListView<p>
 * Author: oubowu<p>
 * Fuction: 图片新闻列表接口<p>
 * CreateDate: 2016/2/21 1:35<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface IPhotoListView extends BaseView {

    void updatePhotoList(List<SinaPhotoList.DataEntity.PhotoListEntity> data, @NonNull String errorMsg, @DataLoadType.DataLoadTypeChecker int type);

}
