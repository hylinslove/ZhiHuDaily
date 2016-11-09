package demo.liuchen.com.zhihudiary.service;

import demo.liuchen.com.zhihudiary.modle.bean.ThemeBean;
import demo.liuchen.com.zhihudiary.modle.bean.TitleBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by meng on 2016/11/3.
 */

public interface GetDataService {

    @GET("api/4/news/latest")
    Observable<String> getNewsBean();

    @GET("api/4/news/before/{date}")
    Observable<String> getBeforeNews(@Path("date") String date);

    @GET("api/4/news/{id}")
    Observable<String> getNewsDetails(@Path("id") int id);

    @GET("api/4/themes")
    Observable<TitleBean> getTitlesData();

    @GET("api/4/theme/{id}")
    Observable<ThemeBean> getThemeData(@Path("id") int id);
}
