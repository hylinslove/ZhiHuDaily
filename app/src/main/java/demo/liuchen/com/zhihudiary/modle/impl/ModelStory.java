package demo.liuchen.com.zhihudiary.modle.impl;

import com.google.gson.Gson;
import com.greendao.Story;
import com.greendao.StoryDao;

import java.util.List;


import demo.liuchen.com.zhihudiary.app.MyApp;
import demo.liuchen.com.zhihudiary.db.GreenDaoHelper;
import demo.liuchen.com.zhihudiary.modle.bean.StoryBean;
import demo.liuchen.com.zhihudiary.modle.model.IModelStory;
import demo.liuchen.com.zhihudiary.presenter.listener.DetailGetListener;
import demo.liuchen.com.zhihudiary.service.GetDataService;
import demo.liuchen.com.zhihudiary.util.RetrofitUtil;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by meng on 2016/11/4.
 */

public class ModelStory implements IModelStory {

    private Gson gson = new Gson();

    @Override
    public void getData(final int id, final DetailGetListener detailGetListener) {

        de.greenrobot.dao.query.Query<Story> query = GreenDaoHelper.getStoryDao()
                .queryBuilder()
                .where(StoryDao.Properties.NewsId.eq(id))
                .build();
        List stories = query.list();


        if(stories.size()>0){
            Story story = (Story) stories.get(0);
            detailGetListener.detailGot(gson.fromJson(story.getJson(),StoryBean.class));
            return;
        }

        RetrofitUtil.getScalarsRetrofit().create(GetDataService.class).getNewsDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, StoryBean>() {
                    @Override
                    public StoryBean call(String s) {
                        GreenDaoHelper.getStoryDao().insert(new Story(null,id,s));

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
