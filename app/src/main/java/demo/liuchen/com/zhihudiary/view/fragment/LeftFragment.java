package demo.liuchen.com.zhihudiary.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import demo.liuchen.com.zhihudiary.R;
import demo.liuchen.com.zhihudiary.modle.bean.TitleBean;
import demo.liuchen.com.zhihudiary.presenter.PresenterTitle;
import demo.liuchen.com.zhihudiary.presenter.listener.ReplaceFragmentListener;
import demo.liuchen.com.zhihudiary.view.IViewTitle;


public class LeftFragment extends Fragment implements IViewTitle {

    private Context context;
    private View view;
    private ScrollView scrollview;
    private PresenterTitle presenterTitle;
    private ListView listview;
    //theme网址的后缀名
    private int themeId;

    private ReplaceFragmentListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        listener = (ReplaceFragmentListener) getActivity();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_left, container, false);

        listview = (ListView) view.findViewById(R.id.List_Title);
        scrollview = (ScrollView) view.findViewById(R.id.scroll_View);

        presenterTitle = new PresenterTitle(this);

        presenterTitle.getDataFromInternet();

        return view;
    }


    //接口中的方法 的导数据

    @Override
    public void TitleDataGot(final TitleBean titleBean) {
        //在此方法中实现适配，显示到屏幕上
        String[] titles = new String[12];
        for (int j = 0; j < titleBean.getOthers().size(); j++) {

            Log.d("TAG", titleBean.getOthers().get(j).getName());
            titles[j] = titleBean.getOthers().get(j).getName();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, titles);

        listview.setAdapter(adapter);
        //测量listview方法
        listview.setLayoutParams(getListParams());
        //解决显示不在顶部的问题
        scrollview.smoothScrollTo(0, 20);
        listview.setFocusable(false);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //item的监听事件得到id传给碎片网址加载下一项
                themeId = titleBean.getOthers().get(position).getId();
                listener.replaceFragment(themeId);
            }
        });

    }

    @Override
    public void TitleFailed() {
        Toast.makeText(getActivity(), "获取失败，请检查网络", Toast.LENGTH_SHORT).show();
    }

    //计算出List高度大小 解决显示不全的问题
    public ViewGroup.LayoutParams getListParams() {
        ListAdapter listAdapter = listview.getAdapter();

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listview);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listview.getLayoutParams();

        params.height = totalHeight + (listview.getDividerHeight() * (listAdapter.getCount() - 1));

        return params;
    }

}
