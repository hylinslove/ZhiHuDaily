package demo.liuchen.com.zhihudiary.presenter;

import demo.liuchen.com.zhihudiary.model.bean.NewsBean;
import demo.liuchen.com.zhihudiary.model.impl.ModelMain;
import demo.liuchen.com.zhihudiary.view.IViewMain;

/**
 * Created by meng on 2016/11/3.
 *
 */

public class PresenterMain {

    private IViewMain iViewMain;
    private ModelMain modelMain;

    public PresenterMain(IViewMain iViewMain) {
        this.iViewMain = iViewMain;
        modelMain = new ModelMain();
    }

    public void getDataFromInternet() {
        modelMain.getData(new DataGetListener() {
            @Override
            public void dataGot(NewsBean bean) {

                iViewMain.bannerDataGot(bean);
                iViewMain.newsDataGot(bean);
            }

            @Override
            public void dataGotFailure() {
                iViewMain.dataGotFailure();

            }
        });
    }

    public void reFresh(){
        modelMain.getData(new DataGetListener() {
            @Override
            public void dataGot(NewsBean bean) {
                iViewMain.refreshSuccess(bean);
            }

            @Override
            public void dataGotFailure() {
                iViewMain.refreshFailure();
            }
        });
    }

    public void getDataFromDB(){

    }

    public void loadMore(String data){
        modelMain.loadMore(data, new DataGetListener() {
            @Override
            public void dataGot(NewsBean bean) {
                iViewMain.loadMoreSuccess(bean);
            }

            @Override
            public void dataGotFailure() {
                iViewMain.loadMoreFailure();

            }
        });

    }


}
