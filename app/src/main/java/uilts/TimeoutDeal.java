package uilts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.WindowManager;

import org.greenrobot.eventbus.EventBus;

import Events.RestartEvent;

/**
 * Created by Constantine on 2016/5/27.
 */
public class TimeoutDeal {

    public static void overTime(Context context) {
        final Dialog alertDialog = new AlertDialog.Builder(context)
                .setMessage("系统超时,请重新登陆")
                .setPositiveButton("重新登陆", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EventBus.getDefault().post(new RestartEvent());
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).create();
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }
}
