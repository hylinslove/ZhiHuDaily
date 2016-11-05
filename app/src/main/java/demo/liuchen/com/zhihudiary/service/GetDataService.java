package demo.liuchen.com.zhihudiary.service;

import demo.liuchen.com.zhihudiary.model.bean.NewsBean;
import demo.liuchen.com.zhihudiary.model.bean.StoryBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by meng on 2016/11/3.
 */

public interface GetDataService {

    @GET("api/4/news/latest")
    Observable<NewsBean> getNewsBean();

    @GET("api/4/news/before/{id}")
    Observable<NewsBean> getBeforeNews(@Path("id") String id);

    @GET("api/4/news/{id}")
    Observable<StoryBean> getNewsDetails(@Path("id") int id);
}
