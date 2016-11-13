package demo.liuchen.com.zhihudiary.view.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import demo.liuchen.com.zhihudiary.R;
import demo.liuchen.com.zhihudiary.presenter.listener.ReplaceFragmentListener;
import demo.liuchen.com.zhihudiary.view.fragment.MainFragment;
import demo.liuchen.com.zhihudiary.view.fragment.ThemeFragment;


public class MainActivity extends AppCompatActivity implements ReplaceFragmentListener{
    private FragmentManager manager = getSupportFragmentManager();
    private FragmentTransaction transition = manager.beginTransaction();
    private MainFragment mainFragment;
    private DrawerLayout drawer;
    private boolean isMainActivity = true;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = (DrawerLayout) findViewById(R.id.drawer_Main);
        initFragment();

        initDonate();

    }

    private void initFragment() {
        mainFragment = new MainFragment();
        transition.add(R.id.frameLayout_main,mainFragment);
        transition.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.Donate:
                donate();
                break;
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void replaceFragment(int id) {
        ThemeFragment themeFragment = ThemeFragment.getFragment(id);
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frameLayout_main,themeFragment);
        transaction.commit();
        drawer.closeDrawer(GravityCompat.START);
        isMainActivity = false;

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK && isMainActivity){
            this.finish();
            return true;
        } else if (keyCode==KeyEvent.KEYCODE_BACK && !isMainActivity){
            FragmentTransaction transaction3 = manager.beginTransaction();
            MainFragment mainFragment2 = new MainFragment();
            transaction3.replace(R.id.frameLayout_main,mainFragment2);
            transaction3.commit();
            isMainActivity = true;
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public  void donate(){
        builder.show();
    }
    public void initDonate(){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("支付宝:763577618@qq.com");
        builder.setPositiveButton("现在就捐", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("一会儿再捐", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
    }

    public void on(View view) {
        Intent intent  = new Intent(this,ILikeActivity.class);
        startActivity(intent);
        drawer.closeDrawer(GravityCompat.START);
    }
}
