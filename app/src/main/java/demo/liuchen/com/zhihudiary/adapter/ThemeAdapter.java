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
import demo.liuchen.com.zhihudiary.modle.bean.ThemeBean;

/**
 * Created by meng on 2016/11/9.
 */

public class ThemeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ThemeBean bean;
    private LayoutInflater layoutInflater;

    public ThemeAdapter(Context context, ThemeBean bean) {
        this.context = context;
        this.bean = bean;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.theme_item,parent,false);

        ThemeAdapter.MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;

        viewHolder.textView.setText(bean.getStories().get(position).getTitle());

        if(bean.getStories().get(position).getImages()!=null) {


//            Picasso.with(context)
//                    .load(bean.getStories().get(position).getImages().get(0))
//                    .into(viewHolder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return bean == null?0:bean.getStories().size();
    }
    public interface ItemClickListener {
        void onclick(int position);
    }
    private RVadapter.ItemClickListener clickListener;
    public void setItemClickListener(RVadapter.ItemClickListener clickListener){
        this.clickListener = clickListener;
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView textView;
        public ImageView imageView;
        public MyViewHolder(View itemView) {
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
