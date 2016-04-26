package uilts;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import callbacks.OkHttpCallBack;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cii on 2016/3/25.
 */
public class OKHttpManager {

    private static OKHttpManager mOKHttpManager;
    private OkHttpClient mOkHttpClient;
    private Handler mDeliveryHander;

    public static OKHttpManager getInstance() {
        if (mOKHttpManager == null) {
            synchronized (OKHttpManager.class) {
                if (mOKHttpManager == null) {
                    mOKHttpManager = new OKHttpManager(new OkHttpClient(), new Handler(Looper.getMainLooper()));
                }
            }
        }
        return mOKHttpManager;
    }

    private OKHttpManager(OkHttpClient okHttpClient, Handler handler) {
        this.mOkHttpClient = okHttpClient;
        this.mDeliveryHander = handler;
    }

    public void setConnectTimeout(int timeout, TimeUnit units) {
        mOkHttpClient = mOkHttpClient.newBuilder().connectTimeout(timeout, units).build();
    }

    public Response execute(Request request) throws IOException {
        return mOkHttpClient.newCall(request).execute();
    }

    public void enqueue(Request request, final OkHttpCallBack okHttpCallBack) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String stringResponse = okHttpCallBack.parseNetworkResponse(response);
                    sendSuccessResultCallback(stringResponse, okHttpCallBack);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void sendSuccessResultCallback(final String response, final OkHttpCallBack okHttpCallBack) {
        if (response == null)
            return;

        mDeliveryHander.post(new Runnable() {
            @Override
            public void run() {
                okHttpCallBack.onResponse(response);
            }
        });
    }
}
