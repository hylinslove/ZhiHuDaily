package demo.liuchen.com.zhihudiary.view.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import demo.liuchen.com.zhihudiary.R;

public class SplashActivity extends Activity {
    private ImageView bottomSplash;
    private ImageView bgImage;

    private TextView timeText;
    int count = 4;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        bottomSplash = (ImageView) findViewById(R.id.bottom_Splash);
        bgImage = (ImageView) findViewById(R.id.bg_Splash);
        timeText = (TextView) findViewById(R.id.timeText);

        //timeText白色的框设置不可见
        timeText.setVisibility(View.INVISIBLE);

        animations();

        backgroundAnimator();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new CountDownTimer(4000,1000){
                    @Override
                    public void onTick(long millisUntilFinished) {
                        //timeText白色的框设置可见
                        timeText.setText("广告剩余 " +--count+ "S");
                        timeText.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFinish() {
                        if(!SplashActivity.this.isFinishing()){
                            startActivity(new Intent(SplashActivity.this,MainActivity.class));
                            finish();
                        }

                    }
                }.start();

            }
        },3000);


    }






    //淡入效果
    private void backgroundAnimator() {
        bgImage.setAlpha(0f);
        bgImage.setVisibility(View.VISIBLE);
        bgImage.animate()
                .alpha(1f)
                .setStartDelay(1000)
                .setDuration(1000)
                .setListener(null);
    }

    //动画--属性动画实现背景图片淡入淡出（隐藏显示）--背景实现图片
    private void animations() {
        ObjectAnimator translate = ObjectAnimator.ofFloat(
                bottomSplash, "TranslationY", 150, 0);
        translate.setDuration(1000);
        translate.start();
    }
}