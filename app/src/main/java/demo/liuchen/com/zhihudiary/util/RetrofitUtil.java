package demo.liuchen.com.zhihudiary.util;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by meng on 2016/11/9.
 */

public class RetrofitUtil {

    private static Retrofit retrofit;
    private static Retrofit retrofit2;

    public static Retrofit getGsonRetrofit(){

        if(retrofit == null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        }

        return retrofit;
    }

    public static Retrofit getScalarsRetrofit(){

        if(retrofit2 == null) {

            retrofit2 = new Retrofit.Builder()
                    .baseUrl(Constant.BaseUrl)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        }

        return retrofit2;
    }

}
