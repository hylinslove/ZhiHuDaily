package demo.liuchen.com.zhihudiary.modle.impl;

import demo.liuchen.com.zhihudiary.modle.model.IModeTheme;

import demo.liuchen.com.zhihudiary.presenter.listener.DetailThemeListener;
import demo.liuchen.com.zhihudiary.service.GetDataService;
import demo.liuchen.com.zhihudiary.util.RetrofitUtil;

/**
 * Created by Administrator on 2016/11/8.
 */

public class ModeTheme implements IModeTheme{

    @Override
    public void getData(int id, DetailThemeListener detailThemeListener) {

        RetrofitUtil.getGsonRetrofit().create(GetDataService.class).getthemeDetails(0);

    }
}
