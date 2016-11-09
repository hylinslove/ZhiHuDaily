package demo.liuchen.com.zhihudiary.modle.impl;

import demo.liuchen.com.zhihudiary.modle.bean.ThemeBean;
import demo.liuchen.com.zhihudiary.modle.model.IModeTheme;

import demo.liuchen.com.zhihudiary.presenter.listener.ThemeDataListener;
import demo.liuchen.com.zhihudiary.service.GetDataService;
import demo.liuchen.com.zhihudiary.util.RetrofitUtil;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/8.
 */

public class ModeTheme implements IModeTheme{

    @Override
    public void getData(int id, final ThemeDataListener themeDataListener) {

        RetrofitUtil.getGsonRetrofit().create(GetDataService.class)
                .getThemeData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ThemeBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        themeDataListener.detailGotFailure();
                    }

                    @Override
                    public void onNext(ThemeBean themeBean) {
                        themeDataListener.detailGot(themeBean);

                    }
                });

    }
}
