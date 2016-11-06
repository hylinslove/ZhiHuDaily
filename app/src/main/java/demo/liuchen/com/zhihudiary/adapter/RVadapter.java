package demo.liuchen.com.zhihudiary.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import demo.liuchen.com.zhihudiary.R;
import demo.liuchen.com.zhihudiary.model.bean.BeforeBean;
import demo.liuchen.com.zhihudiary.model.bean.NewsBean;
import demo.liuchen.com.zhihudiary.util.ScreenSizeUtils;

/**
 * Created by meng on 2016/11/4.
 */

public class RVadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private NewsBean newsBean;
    private LayoutInflater layoutInflater;

    public RVadapter(Context context, NewsBean newsBean) {
        this.context = context;
        this.newsBean = newsBean;
        layoutInflater = LayoutInflater.from(context);
    }

    public void changeData(NewsBean newsBean){
        this.newsBean = newsBean;
        notifyDataSetChanged();
    }

    public void addData(BeforeBean newsBean){
        this.newsBean.getStories().addAll(newsBean.getStories());
        notifyDataSetChanged();
    }

    public NewsBean getNewsBean() {
        return newsBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.news_item,parent,false);

        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;

        viewHolder.textView.setText(newsBean.getStories().get(position).getTitle());
        Picasso.with(context)
                .load(newsBean.getStories().get(position).getImages().get(0))
                .into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return newsBean.getStories().size();
    }

    //声明点击监听事件
    public interface ItemClickListener {
        void onclick(int position);
    }
    private ItemClickListener clickListener;
    public void setItemClickListener(ItemClickListener clickListener){
        this.clickListener = clickListener;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textView;
        private ImageView imageView;

        private MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_main);
            imageView = (ImageView) itemView.findViewById(R.id.image_main);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(clickListener != null) {
                        clickListener.onclick(getLayoutPosition());
                    }
                }
            });
        }
    }

}
