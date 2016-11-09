package demo.liuchen.com.zhihudiary.db;

import android.database.sqlite.SQLiteDatabase;

import com.greendao.BeforeDao;
import com.greendao.DaoMaster;
import com.greendao.DaoSession;
import com.greendao.NewsDao;
import com.greendao.StoryDao;

import demo.liuchen.com.zhihudiary.app.MyApp;

/**
 * Created by meng on 2016/11/9.
 */

public class GreenDaoHelper {

    private static NewsDao newsDao;
    private static StoryDao storyDao;
    private static BeforeDao beforeDao;

    public static NewsDao getNewsDao() {

        return newsDao == null ? MyApp.newSession.getNewsDao() : newsDao;
    }

    public static StoryDao getStoryDao() {

        return storyDao == null ? MyApp.storySession.getStoryDao() : storyDao;
    }

    public static BeforeDao getBeforeDao() {

        return beforeDao == null ? MyApp.beforeSession.getBeforeDao() : beforeDao;
    }
}
