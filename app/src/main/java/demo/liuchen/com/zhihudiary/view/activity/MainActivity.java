package demo.liuchen.com.zhihudiary.view.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import demo.liuchen.com.zhihudiary.R;
import demo.liuchen.com.zhihudiary.view.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager manager = getSupportFragmentManager();
    private FragmentTransaction transition = manager.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();

    }

    private void initFragment() {
        MainFragment mainFragment = new MainFragment();

        transition.add(R.id.frameLayout_main,mainFragment);
        transition.commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
}
