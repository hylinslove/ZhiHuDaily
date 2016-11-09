package demo.liuchen.com.zhihudiary.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import demo.liuchen.com.zhihudiary.R;
import demo.liuchen.com.zhihudiary.adapter.RVadapter;
import demo.liuchen.com.zhihudiary.adapter.ThemeAdapter;
import demo.liuchen.com.zhihudiary.modle.bean.ThemeBean;
import demo.liuchen.com.zhihudiary.presenter.PresenterTheme;
import demo.liuchen.com.zhihudiary.view.IViewTheme;
import demo.liuchen.com.zhihudiary.view.activity.ThemeStoryActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThemeFragment extends Fragment implements IViewTheme {

    @Bind(R.id.recyclerView_theme)
    RecyclerView recyclerView;
    @Bind(R.id.image_theme)
    ImageView imageView;
    @Bind(R.id.toolbar_theme)
    Toolbar toolbar;
    @Bind(R.id.title_theme)
    TextView textView;

    private Context context;
    private PresenterTheme presenterTheme;

    public static ThemeFragment getFragment(int id){
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        ThemeFragment fragment = new ThemeFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_theme, container, false);
        ButterKnife.bind(this,view);


        presenterTheme = new PresenterTheme(this);
        presenterTheme.transData(getArguments().getInt("id"));

        initToolbar();
        initRecyclerView();

        return view;
    }


    @Override
    public void dataGot(final ThemeBean themeBean) {
        initHead(themeBean.getImage(),themeBean.getDescription());
        ThemeAdapter adapter = new ThemeAdapter(context,themeBean);
        adapter.setItemClickListener(new RVadapter.ItemClickListener() {
            @Override
            public void onclick(int position) {
                Intent intent = new Intent(context, ThemeStoryActivity.class);
                intent.putExtra("id",themeBean.getStories().get(position).getId());

                ThemeFragment.this.getActivity().startActivity(intent);

            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void dataGotFailure() {
        Toast.makeText(getActivity(),"数据获取失败，请检查网络",Toast.LENGTH_SHORT).show();
    }


    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    private void initHead(String url,String title) {
        Picasso.with(context).load(url).into(imageView);
        textView.setText(title);
    }


    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setFocusable(false);
    }

}
