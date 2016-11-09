package demo.liuchen.com.zhihudiary.presenter.listener;

import demo.liuchen.com.zhihudiary.modle.bean.NewsBean;

/**
 * Created by meng on 2016/11/3.
 */

public interface DataGetListener {

    void dataGot(NewsBean bean);
    void dataGotFailure();

}
