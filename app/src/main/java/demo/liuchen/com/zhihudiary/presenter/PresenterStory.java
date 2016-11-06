package demo.liuchen.com.zhihudiary.presenter;

import demo.liuchen.com.zhihudiary.model.bean.StoryBean;
import demo.liuchen.com.zhihudiary.model.impl.ModelStory;
import demo.liuchen.com.zhihudiary.model.model.IModelStory;
import demo.liuchen.com.zhihudiary.presenter.listener.DetailGetListener;
import demo.liuchen.com.zhihudiary.view.IViewStory;

/**
 * Created by meng on 2016/11/4.
 */

public class PresenterStory {

    private IModelStory modelStory ;
    private IViewStory viewStory;

    public PresenterStory(IViewStory viewStory) {
        this.viewStory = viewStory;
        modelStory = new ModelStory();
    }

    public void getData() {
        modelStory.getData(viewStory.getContentId(), new DetailGetListener() {
            @Override
            public void detailGot(StoryBean bean) {
                viewStory.setContent(bean);
            }

            @Override
            public void detailGotFailure() {
                viewStory.detailGotFailure();
            }
        });
    }
}
