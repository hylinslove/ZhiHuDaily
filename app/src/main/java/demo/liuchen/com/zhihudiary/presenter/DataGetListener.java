package demo.liuchen.com.zhihudiary.presenter;

import demo.liuchen.com.zhihudiary.model.bean.NewsBean;
import demo.liuchen.com.zhihudiary.model.bean.StoryBean;

/**
 * Created by meng on 2016/11/3.
 */

public interface DataGetListener {

    void dataGot(NewsBean bean);
    void dataGotFailure();

}
