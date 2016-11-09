package demo.liuchen.com.zhihudiary.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.liuchen.com.zhihudiary.R;
import demo.liuchen.com.zhihudiary.adapter.BannerAdapter;
import demo.liuchen.com.zhihudiary.adapter.RVadapter;
import demo.liuchen.com.zhihudiary.modle.bean.BeforeBean;
import demo.liuchen.com.zhihudiary.modle.bean.NewsBean;
import demo.liuchen.com.zhihudiary.presenter.PresenterMain;
import demo.liuchen.com.zhihudiary.presenter.listener.LoadMoreComplete;
import demo.liuchen.com.zhihudiary.util.ScreenSizeUtils;
import demo.liuchen.com.zhihudiary.view.IViewMain;
import demo.liuchen.com.zhihudiary.view.activity.StoryActivity;
import demo.liuchen.com.zhihudiary.view.myView.AutoLoadScrollView;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

/**
 * Created by meng on 2016/11/4.
 * 主界面的碎片:
 * 1.banner轮播图
 * 2.新闻列表
 */

public class MainFragment extends Fragment implements IViewMain ,LoadMoreComplete {
    @Bind(R.id.recyclerView_main)
    RecyclerView recyclerView;
    @Bind(R.id.viewPager_main)
    ViewPager viewPager;
    @Bind((R.id.vp_indicator))
    CirclePageIndicator indicator;
    @Bind(R.id.toolBar_main)
    Toolbar toolbar;
    @Bind(R.id.refresh_main)
    WaveSwipeRefreshLayout mainWsrefresh;
    @Bind(R.id.scroll_main)
    AutoLoadScrollView scrollView;

    private View view;
    private PresenterMain presenterMain;
    private Context context;
    private int index = 0;
    private RVadapter rvAdapter;
    private boolean isLoading = false;
    private String date;
    private int nowPages = 1;
    private boolean isBannerLoaded = false;

    //Attach方法中获取宿主Activity的上下文
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    //OnCreateView方法返回碎片内容
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        presenterMain = new PresenterMain(this);

        initToolbar();
        initViewPager();
        initRecyclerView();
        initRefresh();
        initScrollLoad();
        presenterMain.getDataFromInternet();

        return view;
    }

    //首页轮播图返回数据并回调以下方法:
    @Override
    public void bannerDataGot(NewsBean bean) {
        if (!isBannerLoaded) {
            date = bean.getDate();
            isBannerLoaded = true;
            BannerAdapter bannerAdapter = new BannerAdapter(bean, context);
            viewPager.setOffscreenPageLimit(4);
            viewPager.setAdapter(bannerAdapter);
            indicator.setViewPager(viewPager);

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    viewPager.post(new Runnable() {
                        @Override
                        public void run() {
                            viewPager.setCurrentItem(++index % 5);
                        }
                    });
                }
            }, 4000, 4000);
        }
    }

    //首页消息列表返回数据并回调以下方法:
    @Override
    public void newsDataGot(final NewsBean bean) {
        rvAdapter = new RVadapter(context, bean,this);
        rvAdapter.setItemClickListener(new RVadapter.ItemClickListener() {
            @Override
            public void onclick(int position) {
                NewsBean newsBean = rvAdapter.getNewsBean();
                int id = newsBean.getStories().get(position).getId();
                Intent intent = new Intent(context, StoryActivity.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });
        recyclerView.setAdapter(rvAdapter);
    }

    @Override
    public void dataGotFailure() {
        Toast.makeText(context, "获取数据失败...", Toast.LENGTH_SHORT).show();
    }

    //下拉更新数据返回结果:
    @Override
    public void refreshSuccess(NewsBean bean) {
        rvAdapter.changeData(bean);
        Toast.makeText(context, "刷新成功", Toast.LENGTH_SHORT).show();
        mainWsrefresh.setRefreshing(false);
        nowPages = 1;
    }

    @Override
    public void refreshFailure() {
        Toast.makeText(context, "获取数据失败...", Toast.LENGTH_SHORT).show();
        mainWsrefresh.setRefreshing(false);
    }

    //上拉加载更多内容的数据返回结果:
    @Override
    public void loadMoreSuccess(BeforeBean bean) {
        rvAdapter.addData(bean);
        Toast.makeText(context, "加载成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadMoreFailure() {
        Toast.makeText(context, "加载失败...", Toast.LENGTH_SHORT).show();
        isLoading = false;
    }

    //初始化Toobar
    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer = (DrawerLayout) MainFragment.this.getActivity().findViewById(R.id.drawer_Main);
                drawer.openDrawer(GravityCompat.START);
            }
        });
    }

    //初始化ViewPager
    private void initViewPager() {
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                index = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    //初始化RecyclerView
    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
    }

    //初始化下拉刷新控件
    private void initRefresh() {
        mainWsrefresh.setColorSchemeColors(Color.WHITE);
        mainWsrefresh.setWaveColor(ContextCompat.getColor(context, R.color.colorPrimary));
        mainWsrefresh.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenterMain.reFresh();

            }
        });
    }

    //初始化上拉加载事件
    private void initScrollLoad() {

        scrollView.setScrollListener(new AutoLoadScrollView.ScrollListener() {
            @Override
            public void onScrollListener(int l, int t, int oldl, int oldt) {
                if (!isLoading && scrollView.getChildAt(0).getMeasuredHeight()
                        - ScreenSizeUtils.getScreenHeight(MainFragment.this.getActivity())
                        + toolbar.getMeasuredHeight() + 60 <= t) {
                    isLoading = true;
                    presenterMain.loadMore(String.valueOf(Integer.parseInt(date) - nowPages++));
                }
            }
        });
    }

    @Override
    public void LoadMoreCompleted() {
        isLoading = false;
    }
}
