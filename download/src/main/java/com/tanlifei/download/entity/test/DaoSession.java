package com.tanlifei.download.entity.test;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.tanlifei.download.entity.test.DownloadEntry;

import com.tanlifei.download.entity.test.DownloadEntryDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig downloadEntryDaoConfig;

    private final DownloadEntryDao downloadEntryDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        downloadEntryDaoConfig = daoConfigMap.get(DownloadEntryDao.class).clone();
        downloadEntryDaoConfig.initIdentityScope(type);

        downloadEntryDao = new DownloadEntryDao(downloadEntryDaoConfig, this);

        registerDao(DownloadEntry.class, downloadEntryDao);
    }
    
    public void clear() {
        downloadEntryDaoConfig.getIdentityScope().clear();
    }

    public DownloadEntryDao getDownloadEntryDao() {
        return downloadEntryDao;
    }

}