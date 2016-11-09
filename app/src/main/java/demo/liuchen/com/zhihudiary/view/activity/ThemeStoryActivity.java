package demo.liuchen.com.zhihudiary.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import demo.liuchen.com.zhihudiary.R;
import demo.liuchen.com.zhihudiary.modle.bean.StoryBean;
import demo.liuchen.com.zhihudiary.presenter.PresenterStory;
import demo.liuchen.com.zhihudiary.util.HtmlUtils;
import demo.liuchen.com.zhihudiary.view.IViewStory;

public class ThemeStoryActivity extends AppCompatActivity implements IViewStory{

    private WebView webView;
    private Toolbar toolbar;
    private Intent intent;
    private PresenterStory presenterStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_story);
        webView = (WebView) findViewById(R.id.web_theme);

        initToolbar();
        initWebView();
        intent = getIntent();

        presenterStory = new PresenterStory(this);
        presenterStory.getData();

    }



    @Override
    public int getContentId() {
        return intent.getIntExtra("id",0);
    }

    @Override
    public void setContent(StoryBean storyBean) {
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
        toolbar = (Toolbar) findViewById(R.id.toolBar_theme_story);
        toolbar.setTitle("内容");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThemeStoryActivity.this.finish();
            }
        });
    }
}
