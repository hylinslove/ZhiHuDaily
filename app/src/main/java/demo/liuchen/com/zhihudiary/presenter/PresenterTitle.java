package demo.liuchen.com.zhihudiary.presenter;

import demo.liuchen.com.zhihudiary.modle.bean.TitleBean;
import demo.liuchen.com.zhihudiary.modle.impl.ModelTitle;
import demo.liuchen.com.zhihudiary.presenter.listener.TitleDataListener;
import demo.liuchen.com.zhihudiary.view.IViewTitle;

/**
 * Created by Administrator on 2016/11/5.
 */

public class PresenterTitle {
    private IViewTitle iViewTitle;
    private ModelTitle modelTitle;

    public PresenterTitle(IViewTitle iViewTitle){
        this.iViewTitle =iViewTitle;
        modelTitle= new ModelTitle();
    }
        //得到model中的数据
    public void getDataFromInternet(){
        modelTitle.getData(new TitleDataListener() {
            @Override
            public void dataGot(TitleBean titleBean) {
                iViewTitle.TitleDataGot(titleBean);
            }

            @Override
            public void dataGotFailure() {
                iViewTitle.TitleFailed();
            }
        });

    }



}
