package demo.liuchen.com.zhihudiary.model.impl;

import demo.liuchen.com.zhihudiary.model.bean.NewsBean;
import demo.liuchen.com.zhihudiary.model.model.IModelMain;
import demo.liuchen.com.zhihudiary.presenter.DataGetListener;
import demo.liuchen.com.zhihudiary.service.GetDataService;
import demo.liuchen.com.zhihudiary.util.Constant;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by meng on 2016/11/3.
 */

public class ModelMain implements IModelMain {

    private Retrofit retrofit;
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constant.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    @Override
    public void getData(final DataGetListener dataGetListener) {



        retrofit = builder.build();
        retrofit.create(GetDataService.class).getNewsBean()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( new Action1<NewsBean>() {
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

    public void loadMore(String data,final DataGetListener dataGetListener) {
        Retrofit retrofit2 = builder.build();
        retrofit2.create(GetDataService.class).getBeforeNews(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( new Action1<NewsBean>() {
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
}
