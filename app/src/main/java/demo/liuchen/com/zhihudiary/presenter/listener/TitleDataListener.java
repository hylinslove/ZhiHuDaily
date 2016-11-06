package demo.liuchen.com.zhihudiary.presenter.listener;

import demo.liuchen.com.zhihudiary.model.bean.NewsBean;
import demo.liuchen.com.zhihudiary.model.bean.TitleBean;

/**
 * Created by Administrator on 2016/11/5.
 */

public interface TitleDataListener {
    void dataGot(TitleBean titleBean);
    void dataGotFailure();
}
