package demo.liuchen.com.zhihudiary.view.myView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by meng on 2016/11/4.
 */

public class AutoLoadScrollView extends ScrollView {
    public AutoLoadScrollView(Context context) {
        super(context);
    }

    public AutoLoadScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public interface ScrollListener{
        void onScrollListener(int l,int t,int oldl,int oldt);
    }
    private ScrollListener scrollListener;
    public void setScrollListener(ScrollListener scrollListener){
        this.scrollListener = scrollListener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if(scrollListener != null) {
            scrollListener.onScrollListener(l,t,oldl,oldt);
        }

    }
}
