package com.laowojianada2802.ounews.web;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

public class App extends Application{
    private static  App mApp = null;
    public List<WeakReference<Activity>> aliveActivitys = new ArrayList<WeakReference<Activity>>();
    
    @Override
    public void onCreate() {
    	// TODO Auto-generated method stub
    	super.onCreate();
        mApp = this;
    }
    public static App getInstance(){
        if (mApp == null)
        {
            mApp = new App();
        }
        return mApp;
    }
    public void finishAllActivity(){
        for (int i = 0;i < aliveActivitys.size();i++)
        {
            if (aliveActivitys.get(i) != null)
            {
                aliveActivitys.get(i).get().finish();
            }
        }
    }
    public Activity getTopActivity(){
        return aliveActivitys.get(aliveActivitys.size() - 1).get();
    }

}
