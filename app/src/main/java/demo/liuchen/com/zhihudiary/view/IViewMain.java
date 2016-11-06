package demo.liuchen.com.zhihudiary.view;

import java.util.List;

import demo.liuchen.com.zhihudiary.model.bean.BeforeBean;
import demo.liuchen.com.zhihudiary.model.bean.NewsBean;

/**
 * Created by meng on 2016/11/3.
 */

public interface IViewMain {

    void bannerDataGot(NewsBean bean);
    void newsDataGot(NewsBean bean);
    void dataGotFailure();

    void refreshSuccess(NewsBean bean);
    void refreshFailure();

    void loadMoreSuccess(BeforeBean bean);
    void loadMoreFailure();
}
