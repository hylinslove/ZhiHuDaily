package demo.liuchen.com.zhihudiary.adapter;

import android.content.Context;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import com.squareup.picasso.Picasso;


import java.util.HashMap;
import java.util.Map;

import demo.liuchen.com.zhihudiary.R;
import demo.liuchen.com.zhihudiary.modle.bean.NewsBean;
import demo.liuchen.com.zhihudiary.view.activity.StoryActivity;

/**
 * Created by meng on 2016/10/13.
 *
 */
public class BannerAdapter extends PagerAdapter {

    private NewsBean newsBean;
    private Context context;
    private LayoutInflater inflater;
    private View view;

    private Map<Integer, ImageView> cache = new HashMap<>();

    public BannerAdapter(NewsBean beanMain, Context context) {
        this.newsBean = beanMain;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    public void setBeanMain(NewsBean beanMain) {

        this.newsBean = beanMain;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        view = new View(context);
        view = inflater.inflate(R.layout.banner_item, null);


        ImageView imageView = (ImageView) view.findViewById(R.id.banner_image);

        TextView textView = (TextView) view.findViewById(R.id.text_banner);

        if(newsBean != null) {

            textView.setText(newsBean.getTop_stories().get(position).getTitle());

            String url = newsBean.getTop_stories().get(position).getImage();

            Picasso.with(context)
                    .load(url)
                    .into(imageView);
            cache.put(position, imageView);

        } else {
            textView.setText("网络连接失败~");
        }

        container.addView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = newsBean.getTop_stories().get(position).getId();
                Intent intent = new Intent(context, StoryActivity.class);
                intent.putExtra("id", id);
                context.startActivity(intent);
            }
        });

        return view;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(cache.get(position));
    }


    @Override
    public int getCount() {

        return newsBean == null ? 0:newsBean.getTop_stories().size();

    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
