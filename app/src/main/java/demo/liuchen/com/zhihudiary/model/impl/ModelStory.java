package demo.liuchen.com.zhihudiary.model.impl;

import android.util.Log;

import com.google.gson.Gson;
import com.greendao.Before;
import com.greendao.BeforeDao;
import com.greendao.Story;
import com.greendao.StoryDao;

import java.util.List;


import demo.liuchen.com.zhihudiary.app.MyApp;
import demo.liuchen.com.zhihudiary.model.bean.BeforeBean;
import demo.liuchen.com.zhihudiary.model.bean.StoryBean;
import demo.liuchen.com.zhihudiary.model.model.IModelStory;
import demo.liuchen.com.zhihudiary.presenter.listener.DetailGetListener;
import demo.liuchen.com.zhihudiary.service.GetDataService;
import demo.liuchen.com.zhihudiary.util.Constant;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by meng on 2016/11/4.
 */

public class ModelStory implements IModelStory {

    private Gson gson = new Gson();
    private Retrofit retrofit;
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constant.BaseUrl)
//            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());


    @Override
    public void getData(final int id, final DetailGetListener detailGetListener) {

        de.greenrobot.dao.query.Query<Story> query = MyApp.getStoryDao()
                .queryBuilder()
                .where(StoryDao.Properties.NewsId.eq(id))
                .build();
        List stories = query.list();


        if(stories.size()>0){
            Story story = (Story) stories.get(0);
            detailGetListener.detailGot(gson.fromJson(story.getJson(),StoryBean.class));
            return;
        }

        retrofit = builder.build();
        retrofit.create(GetDataService.class).getNewsDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, StoryBean>() {
                    @Override
                    public StoryBean call(String s) {
                        MyApp.getStoryDao().insert(new Story(null,id,s));

                        return gson.fromJson(s,StoryBean.class);
                    }
                })
                .subscribe(new Action1<StoryBean>() {
                    @Override
                    public void call(StoryBean storyBean) {
                        detailGetListener.detailGot(storyBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        detailGetListener.detailGotFailure();
                    }
                });

    }
}
