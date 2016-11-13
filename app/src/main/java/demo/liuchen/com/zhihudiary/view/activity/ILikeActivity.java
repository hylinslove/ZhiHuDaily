package demo.liuchen.com.zhihudiary.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.greendao.Story;
import com.greendao.StoryDao;

import java.util.ArrayList;
import java.util.List;

import demo.liuchen.com.zhihudiary.R;
import demo.liuchen.com.zhihudiary.adapter.RVadapter;
import demo.liuchen.com.zhihudiary.app.MyApp;
import demo.liuchen.com.zhihudiary.db.GreenDaoHelper;
import demo.liuchen.com.zhihudiary.modle.bean.NewsBean;
import demo.liuchen.com.zhihudiary.modle.bean.StoryBean;

public class ILikeActivity extends AppCompatActivity {

    private Gson gson = new Gson();
    private RecyclerView rv ;
    private NewsBean newsBean;
    private RVadapter adapter;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilike);

        rv = (RecyclerView) findViewById(R.id.recyclerView_like);
        initToolbar();
        initData();
        initView();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolBar_like);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ILikeActivity.this.finish();
            }
        });
    }

    private void initView() {
//        Log.e("TAG",""+newsBean.getStories().size() );
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RVadapter(this,newsBean,null);

        adapter.setItemClickListener(new RVadapter.ItemClickListener() {
            @Override
            public void onclick(int position) {
                NewsBean newsBean = adapter.getNewsBean();
                int id = newsBean.getStories().get(position).getId();
                Intent intent = new Intent(ILikeActivity.this, StoryActivity.class);
                intent.putExtra("id", id);
                ILikeActivity.this.startActivity(intent);
            }
        });

        rv.setAdapter(adapter);
    }

    private void initData() {

        newsBean = new NewsBean();
        NewsBean.StoriesBean storyBean;
        List<NewsBean.StoriesBean> list = new ArrayList<>();
        int num = MyApp.sp.getInt("num", 0);

        for (int i = 0; i < num; i++) {
            storyBean = new NewsBean.StoriesBean();
            de.greenrobot.dao.query.Query<Story> query = GreenDaoHelper.getStoryDao()
                    .queryBuilder()
                    .where(StoryDao.Properties.NewsId.eq(MyApp.sp.getInt((i+1)+"",0)))
                    .build();
            List stories = query.list();

            Story story = (Story) stories.get(0);
            StoryBean sb = gson.fromJson(story.getJson(),StoryBean.class);
            storyBean.setId(sb.getId());
            storyBean.setTitle(sb.getTitle());
//            Log.e("TAG",sb.getTitle() );
            storyBean.setImages(sb.getImages());

            list.add(storyBean);

        }
        newsBean.setStories(list);
    }
}
