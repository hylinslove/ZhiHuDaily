package demo.liuchen.com.zhihudiary.presenter.listener;

import demo.liuchen.com.zhihudiary.modle.bean.BeforeBean;

/**
 * Created by meng on 2016/11/6.
 */

public interface BeforeGetListener {

    void dataGot(BeforeBean bean);
    void dataGotFailure();


}
