package uilts;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import org.greenrobot.eventbus.EventBus;

import Events.NetworkEvent;

/**
 * Created by Constantine on 2016/5/5.
 */
public class NetworkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {
            if (!NetworkUtils.isNetworkConnected(context)) {
                EventBus.getDefault().post(new NetworkEvent(false));
            }
        }
    }
}
