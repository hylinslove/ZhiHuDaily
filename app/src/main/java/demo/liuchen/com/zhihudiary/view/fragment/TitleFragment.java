package demo.liuchen.com.zhihudiary.view.fragment;


import android.icu.text.IDNA;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import demo.liuchen.com.zhihudiary.R;
import demo.liuchen.com.zhihudiary.model.bean.ThemeBean;
import demo.liuchen.com.zhihudiary.view.IViewTheme;

/**
 * A simple {@link Fragment} subclass.
 */
public class TitleFragment extends Fragment implements IViewTheme {
    //全局变量id网址的后缀

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_title, container, false);
    }

    @Override
    public int getContentId() {
        return 0;
    }

    @Override
    public void setContent(ThemeBean themeBean) {

    }

    @Override
    public void detailGotFailure() {
        Toast.makeText(getActivity(),"获取失败，请检查网络",Toast.LENGTH_SHORT).show();
    }
}
