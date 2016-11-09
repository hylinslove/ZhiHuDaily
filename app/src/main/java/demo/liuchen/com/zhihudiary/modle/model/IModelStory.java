package demo.liuchen.com.zhihudiary.modle.model;

import demo.liuchen.com.zhihudiary.presenter.listener.DetailGetListener;

/**
 * Created by meng on 2016/11/4.
 */

public interface IModelStory {

    void getData(int id, DetailGetListener detailGetListener);
}
