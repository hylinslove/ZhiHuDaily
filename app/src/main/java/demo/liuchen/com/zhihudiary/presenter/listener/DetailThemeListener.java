package demo.liuchen.com.zhihudiary.presenter.listener;

import demo.liuchen.com.zhihudiary.modle.bean.ThemeBean;

/**
 * Created by Administrator on 2016/11/8.
 */

public interface DetailThemeListener {
    void detailGot(ThemeBean bean);
    void detailGotFailure();

}
