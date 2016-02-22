package com.tanlifei.download;


import android.app.Application;
import android.content.Context;

import com.tanlifei.download.entity.test.DaoMaster;

/**
 * 全局Application
 *
 * @author tanlifei
 * @date 2015年8月13日 上午11:30:51
 */
public class BaseApplication extends Application {

    public static Context appContext;
    public static DaoMaster daoMaster;
    public static DaoMaster.DevOpenHelper helper;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        inittDatabase();//greenDAO创建数据表
    }


    /**
     * 设置是否开启全局未捕获异常
     */
    //greenDAO创建数据表
    private void inittDatabase() {
        helper = new DaoMaster.DevOpenHelper(this, "db_download", null);
        daoMaster = new DaoMaster(helper.getWritableDatabase());
    }

}
