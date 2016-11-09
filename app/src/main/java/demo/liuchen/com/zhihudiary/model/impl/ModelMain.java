package demo.liuchen.com.zhihudiary.model.impl;

import com.google.gson.Gson;
import com.greendao.Before;
import com.greendao.BeforeDao;
import com.greendao.News;

import java.util.List;

import demo.liuchen.com.zhihudiary.app.MyApp;
import demo.liuchen.com.zhihudiary.model.bean.BeforeBean;
import demo.liuchen.com.zhihudiary.model.bean.NewsBean;
import demo.liuchen.com.zhihudiary.model.model.IModelMain;
import demo.liuchen.com.zhihudiary.presenter.listener.BeforeGetListener;
import demo.liuchen.com.zhihudiary.presenter.listener.DataGetListener;
import demo.liuchen.com.zhihudiary.service.GetDataService;
import demo.liuchen.com.zhihudiary.util.RetrofitUtil;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by meng on 2016/11/3.
 */

public class ModelMain implements IModelMain {

    private Gson gson = new Gson();

    @Override
    public void getData(final DataGetListener dataGetListener) {

        de.greenrobot.dao.query.Query<News> query = MyApp.getNewsDao()
                .queryBuilder()
                .build();
        List newses = query.list();

        if (newses.size() > 0) {

            News news = (News) newses.get(0);
            dataGetListener.dataGot(gson.fromJson(news.getJson(), NewsBean.class));

        }

        RetrofitUtil.getScalarsRetrofit().create(GetDataService.class).getNewsBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, NewsBean>() {
                    @Override
                    public NewsBean call(String s) {

                        MyApp.getNewsDao().deleteAll();
                        MyApp.getNewsDao().insert(new News(null, s));
                        return gson.fromJson(s, NewsBean.class);
                    }
                })
                .subscribe(new Action1<NewsBean>() {
                    @Override
                    public void call(NewsBean bean) {
                        dataGetListener.dataGot(bean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        dataGetListener.dataGotFailure();
                    }
                });

    }

    public void loadMore(final String date, final BeforeGetListener dataGetListener) {

        de.greenrobot.dao.query.Query<Before> query = MyApp.getBeforeDao()
                .queryBuilder()
                .where(BeforeDao.Properties.Date.eq(date))
                .build();
        List befores = query.list();

        if (befores.size() > 0) {
            Before before = (Before) befores.get(0);
            dataGetListener.dataGot(gson.fromJson(before.getJson(), BeforeBean.class));
            return;
        }

        RetrofitUtil.getScalarsRetrofit().create(GetDataService.class).getBeforeNews(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Func1<String, BeforeBean>() {
                    @Override
                    public BeforeBean call(String s) {
                        MyApp.getBeforeDao().insert(new Before(null, date, s));
                        return gson.fromJson(s, BeforeBean.class);
                    }
                })
                .subscribe(new Action1<BeforeBean>() {
                    @Override
                    public void call(BeforeBean bean) {
                        dataGetListener.dataGot(bean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        dataGetListener.dataGotFailure();
                    }
                });

    }
}
