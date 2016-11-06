package demo.liuchen.com.zhihudiary.model.impl;

import demo.liuchen.com.zhihudiary.model.bean.TitleBean;
import demo.liuchen.com.zhihudiary.model.model.IModeTitle;
import demo.liuchen.com.zhihudiary.presenter.listener.TitleDataListener;
import demo.liuchen.com.zhihudiary.service.GetDataService;
import demo.liuchen.com.zhihudiary.util.Constant;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/5.
 */

public class ModelTitle implements IModeTitle{
    private Retrofit retrofit;

    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constant.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    @Override
    public void getData(final TitleDataListener titleDataListener) {
        retrofit = builder.build();
        retrofit.create(GetDataService.class).getTitlesData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TitleBean>() {
                    @Override
                    public void call(TitleBean titleBean) {
                        titleDataListener.dataGot(titleBean);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                      titleDataListener.dataGotFailure();
                    }
                });
    }
}
