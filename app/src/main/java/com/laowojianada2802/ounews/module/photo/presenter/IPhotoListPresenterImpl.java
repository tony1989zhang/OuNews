package com.laowojianada2802.ounews.module.photo.presenter;

import com.laowojianada2802.ounews.base.BasePresenterImpl;
import com.laowojianada2802.ounews.bean.SinaPhotoList;
import com.laowojianada2802.ounews.common.DataLoadType;
import com.laowojianada2802.ounews.module.photo.model.IPhotoListInteractor;
import com.laowojianada2802.ounews.module.photo.model.IPhotoListInteractorImpl;
import com.laowojianada2802.ounews.module.photo.view.IPhotoListView;
import com.socks.library.KLog;

import java.util.List;

/**
 * ClassName: IPhotoListPresenterImpl<p>
 * Author: oubowu<p>
 * Fuction: 图片列表代理接口实现<p>
 * CreateDate: 2016/2/21 16:15<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public class IPhotoListPresenterImpl extends BasePresenterImpl<IPhotoListView, List<SinaPhotoList.DataEntity.PhotoListEntity>> implements IPhotoListPresenter {

    private IPhotoListInteractor<List<SinaPhotoList.DataEntity.PhotoListEntity>> mPhotoListInteractor;
    private String mPhotoId;
    private int mStartPage;

    private boolean mIsRefresh = true;
    private boolean mHasInit;

    public IPhotoListPresenterImpl(IPhotoListView view, String photoId, int startPage) {
        super(view);
        mPhotoId = photoId;
        mStartPage = startPage;
        mPhotoListInteractor = new IPhotoListInteractorImpl();
        mSubscription = mPhotoListInteractor.requestPhotoList(this, mPhotoId, mStartPage);
    }

    @Override
    public void beforeRequest() {
        if (!mHasInit) {
            mHasInit = true;
            mView.showProgress();
        }
    }

    @Override
    public void requestError(String e) {
        super.requestError(e);
        mView.updatePhotoList(null, e, mIsRefresh ? DataLoadType.TYPE_REFRESH_FAIL : DataLoadType.TYPE_LOAD_MORE_FAIL);
    }

    @Override
    public void refreshData() {
        mStartPage = 1;
        mIsRefresh = true;
        mSubscription = mPhotoListInteractor.requestPhotoList(this, mPhotoId, mStartPage);
    }

    @Override
    public void loadMoreData() {
        KLog.e("加载更多数据: " + mPhotoId + ";" + mStartPage);
        mIsRefresh = false;
        mSubscription = mPhotoListInteractor.requestPhotoList(this, mPhotoId, mStartPage);
    }

    @Override
    public void requestSuccess(List<SinaPhotoList.DataEntity.PhotoListEntity> data) {
        if (data != null && data.size() > 0) {
            mStartPage++;
        }
        mView.updatePhotoList(data, "", mIsRefresh ? DataLoadType.TYPE_REFRESH_SUCCESS : DataLoadType.TYPE_LOAD_MORE_SUCCESS);

    }

}
