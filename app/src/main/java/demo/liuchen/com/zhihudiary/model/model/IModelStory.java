package demo.liuchen.com.zhihudiary.model.model;

import demo.liuchen.com.zhihudiary.presenter.DataGetListener;
import demo.liuchen.com.zhihudiary.presenter.DetailGetListener;

/**
 * Created by meng on 2016/11/4.
 */

public interface IModelStory {

    void getData(int id, DetailGetListener detailGetListener);
}
