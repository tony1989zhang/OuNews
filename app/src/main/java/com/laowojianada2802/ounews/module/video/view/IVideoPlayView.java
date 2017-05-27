package com.laowojianada2802.ounews.module.video.view;

import com.laowojianada2802.ounews.base.BaseView;

/**
 * ClassName: IVideoPlayView<p>
 * Author: oubowu<p>
 * Fuction: 视频播放视图接口<p>
 * CreateDate: 2016/2/23 21:31<p>
 * UpdateUser: <p>
 * UpdateDate: <p>
 */
public interface IVideoPlayView extends BaseView{

    void playVideo(String path, String name);

//    void registerScreenBroadCastReceiver();

}