package demo.liuchen.com.zhihudiary.modle.impl;

import demo.liuchen.com.zhihudiary.modle.bean.TitleBean;
import demo.liuchen.com.zhihudiary.modle.model.IModeTitle;

import demo.liuchen.com.zhihudiary.presenter.listener.TitleDataListener;
import demo.liuchen.com.zhihudiary.service.GetDataService;
import demo.liuchen.com.zhihudiary.util.RetrofitUtil;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/5.
 */

public class ModelTitle implements IModeTitle{

    @Override
    public void getData(final TitleDataListener titleDataListener) {

        RetrofitUtil.getGsonRetrofit().create(GetDataService.class).getTitlesData()
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
