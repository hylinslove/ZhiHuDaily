package demo.liuchen.com.zhihudiary.presenter;

import demo.liuchen.com.zhihudiary.modle.bean.ThemeBean;
import demo.liuchen.com.zhihudiary.modle.impl.ModeTheme;
import demo.liuchen.com.zhihudiary.presenter.listener.ThemeDataListener;
import demo.liuchen.com.zhihudiary.view.IViewTheme;

/**
 * Created by meng on 2016/11/9.
 */

public class PresenterTheme {

    private IViewTheme iViewTheme;
    private ModeTheme modeTheme;

    public PresenterTheme(IViewTheme iViewTheme) {
        this.iViewTheme = iViewTheme;
        modeTheme = new ModeTheme();
    }

    public void transData(int id){
        modeTheme.getData(id, new ThemeDataListener() {
            @Override
            public void detailGot(ThemeBean bean) {
                iViewTheme.dataGot(bean);
            }

            @Override
            public void detailGotFailure() {
                iViewTheme.dataGotFailure();
            }
        });
    }

}
