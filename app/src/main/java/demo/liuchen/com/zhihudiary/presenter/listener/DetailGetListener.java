package demo.liuchen.com.zhihudiary.presenter.listener;

import demo.liuchen.com.zhihudiary.model.bean.StoryBean;

/**
 * Created by meng on 2016/11/4.
 */

public interface DetailGetListener  {

    void detailGot(StoryBean bean);
    void detailGotFailure();
}
