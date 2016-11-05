package demo.liuchen.com.zhihudiary.model.impl;

import demo.liuchen.com.zhihudiary.model.bean.NewsBean;
import demo.liuchen.com.zhihudiary.model.bean.StoryBean;
import demo.liuchen.com.zhihudiary.model.model.IModelStory;
import demo.liuchen.com.zhihudiary.presenter.DataGetListener;
import demo.liuchen.com.zhihudiary.presenter.DetailGetListener;
import demo.liuchen.com.zhihudiary.service.GetDataService;
import demo.liuchen.com.zhihudiary.util.Constant;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by meng on 2016/11/4.
 */

public class ModelStory implements IModelStory {

    private Retrofit retrofit;
    Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(Constant.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    @Override
    public void getData(int id, final DetailGetListener detailGetListener) {

        retrofit = builder.build();
        retrofit.create(GetDataService.class).getNewsDetails(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
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
