package com.greendao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.greendao.Story;
import com.greendao.News;
import com.greendao.Before;

import com.greendao.StoryDao;
import com.greendao.NewsDao;
import com.greendao.BeforeDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig storyDaoConfig;
    private final DaoConfig newsDaoConfig;
    private final DaoConfig beforeDaoConfig;

    private final StoryDao storyDao;
    private final NewsDao newsDao;
    private final BeforeDao beforeDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        storyDaoConfig = daoConfigMap.get(StoryDao.class).clone();
        storyDaoConfig.initIdentityScope(type);

        newsDaoConfig = daoConfigMap.get(NewsDao.class).clone();
        newsDaoConfig.initIdentityScope(type);

        beforeDaoConfig = daoConfigMap.get(BeforeDao.class).clone();
        beforeDaoConfig.initIdentityScope(type);

        storyDao = new StoryDao(storyDaoConfig, this);
        newsDao = new NewsDao(newsDaoConfig, this);
        beforeDao = new BeforeDao(beforeDaoConfig, this);

        registerDao(Story.class, storyDao);
        registerDao(News.class, newsDao);
        registerDao(Before.class, beforeDao);
    }
    
    public void clear() {
        storyDaoConfig.getIdentityScope().clear();
        newsDaoConfig.getIdentityScope().clear();
        beforeDaoConfig.getIdentityScope().clear();
    }

    public StoryDao getStoryDao() {
        return storyDao;
    }

    public NewsDao getNewsDao() {
        return newsDao;
    }

    public BeforeDao getBeforeDao() {
        return beforeDao;
    }

}
