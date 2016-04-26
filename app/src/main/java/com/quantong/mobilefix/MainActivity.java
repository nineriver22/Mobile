package com.quantong.mobilefix;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import viewpagers.RepairFragment;
import viewpagers.PropertyFragment;
import viewpagers.SettingFragment;
import viewpagers.TodoFragment;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private TextView tvTitle;
    private ViewPager viewPager;
    private LinearLayout llTodo;
    private LinearLayout llRepair;
    private LinearLayout llProperty;
    private LinearLayout llSetting;
    private ImageButton ibtnTodo;
    private ImageButton ibtnRepair;
    private ImageButton ibtnProperty;
    private ImageButton ibtnSetting;
    private TextView tvTodo;
    private TextView tvRepair;
    private TextView tvproperty;
    private TextView tvSetting;

    private ArrayList<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle = (TextView) findViewById(R.id.tv_main_title);
        viewPager = (ViewPager) findViewById(R.id.vp_main);
        llTodo = (LinearLayout) findViewById(R.id.ll_mainbottom_todo);
        llRepair = (LinearLayout) findViewById(R.id.ll_mainbottom_repair);
        llProperty = (LinearLayout) findViewById(R.id.ll_mainbottom_property);
        llSetting = (LinearLayout) findViewById(R.id.ll_mainbottom_setting);
        ibtnTodo = (ImageButton) findViewById(R.id.ibtn_maindbottom_todo);
        ibtnRepair= (ImageButton) findViewById(R.id.ibtn_maindbottom_repair);
        ibtnProperty = (ImageButton) findViewById(R.id.ibtn_maindbottom_property);
        ibtnSetting = (ImageButton) findViewById(R.id.ibtn_maindbottom_setting);
        tvTodo = (TextView) findViewById(R.id.tv_maindbottom_todo);
        tvRepair = (TextView) findViewById(R.id.tv_maindbottom_repair);
        tvproperty = (TextView) findViewById(R.id.tv_maindbottom_property);
        tvSetting = (TextView) findViewById(R.id.tv_maindbottom_setting);

        llTodo.setOnClickListener(new MyOnClickListener(0));
        llRepair.setOnClickListener(new MyOnClickListener(1));
        llProperty.setOnClickListener(new MyOnClickListener(2));
        llSetting.setOnClickListener(new MyOnClickListener(3));

        initView();

    }

    private void initView() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new TodoFragment());
        fragmentList.add(new RepairFragment());
        fragmentList.add(new PropertyFragment());
        fragmentList.add(new SettingFragment());

        viewPager.setOffscreenPageLimit(4);
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        viewPager.setCurrentItem(0);

        tvTitle.setText("我的待办");
    }

    @Override
    public void onClick(View v) {

    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {
            switch (arg0) {
                case 0:
                    pageViewSelectedEffect(ibtnTodo, tvTodo, 0);
                    break;
                case 1:
                    pageViewSelectedEffect(ibtnRepair, tvRepair, 1);
                    break;
                case 2:
                    pageViewSelectedEffect(ibtnProperty, tvproperty, 2);
                    break;
                case 3:
                    pageViewSelectedEffect(ibtnSetting, tvSetting, 3);
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
        tvTitle.setText(textView.getText());
        ibtnTodo.setBackgroundResource(R.drawable.iv_mainbottom_todo_un);
        ibtnRepair.setBackgroundResource(R.drawable.iv_mainbottom_repair_un);
        ibtnProperty.setBackgroundResource(R.drawable.iv_mainbottom_property_un);
        ibtnSetting.setBackgroundResource(R.drawable.iv_mainbottom_setting_un);
        tvTodo.setTextColor(Color.rgb(117,119,118));
        tvproperty.setTextColor(Color.rgb(117,119,118));
        tvRepair.setTextColor(Color.rgb(117,119,118));
        tvSetting.setTextColor(Color.rgb(117,119,118));

        if (position == 0)
            imageButton.setBackgroundResource(R.drawable.iv_mainbottom_todo);
        if (position == 1)
            imageButton.setBackgroundResource(R.drawable.iv_mainbottom_repair);
        if (position == 2)
            imageButton.setBackgroundResource(R.drawable.iv_mainbottom_property);
        if (position == 3)
            imageButton.setBackgroundResource(R.drawable.iv_mainbottom_setting);
        textView.setTextColor(Color.rgb(0, 133, 207));
    }
}