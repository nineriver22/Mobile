package com.quantong.mobilefix;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import Events.MenuEvent;
import Events.NetworkEvent;
import callbacks.OkHttpCallBack;
import constants.PersonalConstansts;
import constants.ServiceContans;
import databeans.TestTokenBean;
import fragments.ContentMainFragment;
import fragments.MenuMainFragment;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import uilts.NetworkReceiver;
import uilts.OKHttpManager;
import uilts.SlidingMenu;

public class MainActivity extends FragmentActivity {

    private final String TAG = "MainActivity";
    private NetworkReceiver mNetworkReceiver;

    private SlidingMenu slidingMenu;

    private TestTokenBean testTokenBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        slidingMenu = (SlidingMenu) findViewById(R.id.slm_main);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.ll_main_menu, new MenuMainFragment());
        ft.add(R.id.ll_main_content, new ContentMainFragment());
        ft.commit();

        testToken("wangqiaobian_cz");

        EventBus.getDefault().register(this);
        registerNetworkReceiver();
    }

    private void testToken(final String account) {
        RequestBody requestBody = new FormBody.Builder()
                .add("username", account)
                .build();

        Request request = new Request.Builder().post(requestBody).url(ServiceContans.testToken).build();
        OKHttpManager.getInstance().enqueue(request, new OkHttpCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                Log.e(TAG, "onError: " + "error");
            }

            @Override
            public void onResponse(Object response) {
                resolveTestTokenData(response.toString());
            }
        });
    }

    private void resolveTestTokenData(final String json) {
        Gson gson = new Gson();
        try {
            testTokenBean = gson.fromJson(json, TestTokenBean.class);
            if (testTokenBean.code == 200) {
                PersonalConstansts.token = testTokenBean.datum.token;
            } else {
                Toast.makeText(this, testTokenBean.message, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    private void registerNetworkReceiver() {
        mNetworkReceiver = new NetworkReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetworkReceiver, intentFilter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void slideMenu(MenuEvent menuEvent) {
        slidingMenu.slideMenu();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showNetworkError(NetworkEvent networkEvent) {
        if (!networkEvent.isNetworkConnected) {
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        unregisterReceiver(mNetworkReceiver);
        Log.d(TAG, "onDestroy: ");
    }

}