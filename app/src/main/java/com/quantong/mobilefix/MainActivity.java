package com.quantong.mobilefix;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import fragments.EvaluationFragment;
import fragments.IndexFragment;
import fragments.MenuFragment;
import fragments.TodoFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager fragmentManager;
    private Toolbar toolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout drawerLayout;
    private FrameLayout flContent;
    private View vMaincontent;
    private ViewPager viewPager;
    private LinearLayout llIndex;
    private LinearLayout llTodo;
    private LinearLayout llEvaluation;
    private ImageButton ibtnIndex;
    private ImageButton ibtnTodo;
    private ImageButton ibtnEvaluation;
    private TextView tvIndex;
    private TextView tvTodo;
    private TextView tvEvaluation;

    private ArrayList<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flContent = (FrameLayout) findViewById(R.id.fl_main_content);
        drawerLayout = (DrawerLayout) findViewById(R.id.dl_main);
        toolbar = (Toolbar) findViewById(R.id.tb_main);

        vMaincontent = View.inflate(this, R.layout.maincontent, null);
        viewPager = (ViewPager) vMaincontent.findViewById(R.id.vp_maincontent);
        llIndex = (LinearLayout) vMaincontent.findViewById(R.id.ll_mainbottom_index);
        llTodo = (LinearLayout) vMaincontent.findViewById(R.id.ll_mainbottom_todo);
        llEvaluation = (LinearLayout) vMaincontent.findViewById(R.id.ll_mainbottom_evaluation);
        ibtnIndex = (ImageButton) vMaincontent.findViewById(R.id.ibtn_maindbottom_index);
        ibtnTodo = (ImageButton) vMaincontent.findViewById(R.id.ibtn_maindbottom_todo);
        ibtnEvaluation = (ImageButton) vMaincontent.findViewById(R.id.ibtn_maindbottom_evaluation);
        tvIndex = (TextView) vMaincontent.findViewById(R.id.tv_maindbottom_index);
        tvTodo = (TextView) vMaincontent.findViewById(R.id.tv_maindbottom_todo);
        tvEvaluation = (TextView) vMaincontent.findViewById(R.id.tv_maindbottom_evaluation);
        flContent.addView(vMaincontent);
        llIndex.setOnClickListener(new MyOnClickListener(0));
        llTodo.setOnClickListener(new MyOnClickListener(1));
        llEvaluation.setOnClickListener(new MyOnClickListener(2));

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_main_menu, new MenuFragment());
        fragmentTransaction.commit();
        setSupportActionBar(toolbar);

        toolbar.inflateMenu(R.menu.activity_main_drawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);

        initViewPage();
    }

    private void initViewPage() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new IndexFragment());
        fragmentList.add(new TodoFragment());
        fragmentList.add(new EvaluationFragment());

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {

    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    pageViewSelectedEffect(ibtnIndex, tvIndex, 0);
                    break;
                case 1:
                    pageViewSelectedEffect(ibtnTodo, tvTodo, 1);
                    break;
                case 2:
                    pageViewSelectedEffect(ibtnEvaluation, tvEvaluation, 2);
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragmentList.get(arg0);
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }

    private class MyOnClickListener implements View.OnClickListener {

        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            viewPager.setCurrentItem(index);
        }
    }

    private void pageViewSelectedEffect(ImageButton imageButton, TextView textView, int position) {
        ibtnIndex.setBackgroundResource(R.drawable.iv_mainbottom_index_un);
        ibtnTodo.setBackgroundResource(R.drawable.iv_mainbottom_todo_un);
        ibtnEvaluation.setBackgroundResource(R.drawable.iv_mainbottom_evaluation_un);
        tvIndex.setTextColor(Color.rgb(102, 102, 102));
        tvTodo.setTextColor(Color.rgb(102, 102, 102));
        tvEvaluation.setTextColor(Color.rgb(102, 102, 102));

        if (position == 0)
            imageButton.setBackgroundResource(R.drawable.iv_mainbottom_index);
        if (position == 1)
            imageButton.setBackgroundResource(R.drawable.iv_mainbottom_todo);
        if (position == 2)
            imageButton.setBackgroundResource(R.drawable.iv_mainbottom_evaluation);

        textView.setTextColor(Color.rgb(255, 154, 17));
    }
}