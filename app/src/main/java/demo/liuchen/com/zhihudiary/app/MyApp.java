package demo.liuchen.com.zhihudiary.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;

import com.greendao.DaoMaster;
import com.greendao.DaoSession;


/**
 * Created by Administrator on 2016/11/1.
 */

public class MyApp extends Application {
    public static Context context;
    public static DaoSession newSession;

    public static DaoSession storySession;

    private static DaoMaster beforeMaster;
    public static DaoSession beforeSession;

    public static SharedPreferences.Editor editor;
    public static SharedPreferences sp;
//    private boolean isFirst = true ;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        initNewsDao();
        initBeforeDao();
        initStoryDao();

        initSharePreference();

    }

    private void initSharePreference() {
        sp = getSharedPreferences("fav",Context.MODE_PRIVATE);
        editor = sp.edit();
//        isFirst = sp.getBoolean("isFirst",true);
//        if(isFirst) {
//            editor.putBoolean("isFirst",false);
//            editor.putInt("num",0);
//        }

    }


    public static void initNewsDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context,"news_db",null);
        SQLiteDatabase newsDB = helper.getWritableDatabase();
        DaoMaster newsMaster = new DaoMaster(newsDB);
        newSession = newsMaster.newSession();

    }

    public static void initStoryDao() {
        DaoMaster.DevOpenHelper helper2 = new DaoMaster.DevOpenHelper(context,"story_db",null);
        SQLiteDatabase storyDB = helper2.getWritableDatabase();
        DaoMaster storyMaster = new DaoMaster(storyDB);
        storySession = storyMaster.newSession();

    }

    public static void initBeforeDao(){
        DaoMaster.DevOpenHelper helper3 = new DaoMaster.DevOpenHelper(context,"before_db",null);
        SQLiteDatabase beforeDB = helper3.getWritableDatabase();
        beforeMaster = new DaoMaster(beforeDB);
        beforeSession = beforeMaster.newSession();

    }



}
