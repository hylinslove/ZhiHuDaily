package demo.liuchen.com.zhihudiary.view;

import demo.liuchen.com.zhihudiary.modle.bean.StoryBean;

/**
 * Created by meng on 2016/11/4.
 */

public interface IViewStory {

    int getContentId();
    void setContent(StoryBean storyBean);
    void detailGotFailure();

}
