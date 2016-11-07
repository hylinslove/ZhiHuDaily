package demo.liuchen.com.zhihudiary.app;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.greendao.Before;
import com.greendao.BeforeDao;
import com.greendao.DaoMaster;
import com.greendao.DaoSession;
import com.greendao.NewsDao;
import com.greendao.StoryDao;

import demo.liuchen.com.zhihudiary.R;



/**
 * Created by Administrator on 2016/11/1.
 */

public class MyApp extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }


    public static NewsDao getNewsDao() {
        SQLiteDatabase newsDB ;
        DaoMaster newsMaster;
        DaoSession newSession;
        NewsDao newsDao;
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,"news_db",null);
        newsDB = helper.getWritableDatabase();
        newsMaster = new DaoMaster(newsDB);
        newSession = newsMaster.newSession();
        newsDao = newSession.getNewsDao();

        return newsDao;
    }

    public static StoryDao getStoryDao() {
        SQLiteDatabase storyDB ;
        DaoMaster storyMaster;
        DaoSession storySession;
        StoryDao storyDao;
        DaoMaster.DevOpenHelper helper2 = new DaoMaster.DevOpenHelper(context,"story_db",null);
        storyDB = helper2.getWritableDatabase();
        storyMaster = new DaoMaster(storyDB);
        storySession = storyMaster.newSession();
        storyDao = storySession.getStoryDao();

        return storyDao;
    }

    public static BeforeDao getBeforeDao(){
        SQLiteDatabase beforeDB ;
        DaoMaster beforeMaster;
        DaoSession beforeSession;
        BeforeDao beforeDao;
        DaoMaster.DevOpenHelper helper3 = new DaoMaster.DevOpenHelper(context,"before_db",null);
        beforeDB = helper3.getWritableDatabase();
        beforeMaster = new DaoMaster(beforeDB);
        beforeSession = beforeMaster.newSession();
        beforeDao = beforeSession.getBeforeDao();
        return beforeDao;
    }



}
