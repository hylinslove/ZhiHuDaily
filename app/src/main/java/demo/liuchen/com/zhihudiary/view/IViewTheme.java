package demo.liuchen.com.zhihudiary.view;

import demo.liuchen.com.zhihudiary.modle.bean.ThemeBean;

/**
 * Created by Administrator on 2016/11/8.
 */

public interface IViewTheme {
    int getContentId();
    void setContent(ThemeBean themeBean);
    void detailGotFailure();
}
