package demo.liuchen.com.zhihudiary.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.liuchen.com.zhihudiary.R;
import demo.liuchen.com.zhihudiary.modle.bean.StoryBean;
import demo.liuchen.com.zhihudiary.presenter.PresenterStory;
import demo.liuchen.com.zhihudiary.util.HtmlUtils;
import demo.liuchen.com.zhihudiary.view.IViewStory;

public class StoryActivity extends AppCompatActivity implements IViewStory {

    @Bind(R.id.toolBar_content)
    Toolbar toolbar;
    @Bind(R.id.image_content)
    ImageView imageView;
    @Bind(R.id.text_content)
    TextView titleText;
    @Bind(R.id.imgeSrc_content)
    TextView secTitle;
    @Bind(R.id.web_content)
    WebView webView;

    private Intent intent;
    private PresenterStory presenterStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        ButterKnife.bind(this);

        initToolbar();
        initWebView();
        intent = getIntent();

        presenterStory = new PresenterStory(this);
        presenterStory.getData();
    }


    @Override
    public int getContentId() {
        return intent.getIntExtra("id", -1);
    }

    @Override
    public void setContent(StoryBean storyBean) {
        titleText.setText(storyBean.getTitle());
        secTitle.setText(storyBean.getImage_source());

        Picasso.with(this)
                .load(storyBean.getImage())
                .into(imageView);

        webView.loadData(HtmlUtils.getHtmlCode(storyBean.getBody(), storyBean.getCss().get(0)),
                "text/html; charset=UTF-8",
                null);
    }

    @Override
    public void detailGotFailure() {
        Toast.makeText(this, "获取数据失败...", Toast.LENGTH_SHORT).show();
    }

    private void initWebView() {
        webView.setScrollbarFadingEnabled(true);

        webView.setVerticalScrollBarEnabled(false);

        //能够和js交互
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setAppCacheEnabled(false);
    }

    private void initToolbar() {
        toolbar.setTitle("内容");
        setSupportActionBar(toolbar);
    }


}
