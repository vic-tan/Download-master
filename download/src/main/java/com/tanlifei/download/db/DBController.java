package com.tanlifei.download.db;

import android.content.Context;

import com.tanlifei.download.BaseApplication;
import com.tanlifei.download.entity.test.DownloadEntry;
import com.tanlifei.download.entity.test.DownloadEntryDao;

import java.util.List;

/**
 * @author shuwoom
 * @email 294299195@qq.com
 * @date 2015-9-2
 * @update 2015-9-2
 * @des DBController
 */
public class DBController {
    private static DBController mInstance;
    private DownloadEntryDao dao;

    private DBController(Context context) {
        dao = BaseApplication.daoMaster.newSession().getDownloadEntryDao();
    }

    public static DBController getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new DBController(context);
        }
        return mInstance;
    }

    public synchronized void newOrUpdate(DownloadEntry entry) {
        dao.insertOrReplace(entry);
    }

    public synchronized List<DownloadEntry> queryAll() {
        return dao.loadAll();
    }

    public synchronized DownloadEntry queryByUrl(String url) {
        List<DownloadEntry> list = dao.queryBuilder().where(DownloadEntryDao.Properties.Url.eq(url)).list();
        if (list != null)
            return list.get(0);
        else
            return null;
    }

    public synchronized void deleteByUrl(String url) {
        dao.deleteByKey(url);
    }

}
