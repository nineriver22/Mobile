package fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quantong.mobilefix.R;

import constants.PersonalConstansts;

/**
 * Created by Constantine on 2016/5/6.
 */
public class MenuMainFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "MenuMainFragment";

    private TextView tvUserName;
    private RelativeLayout rlExit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_main, container, false);

        tvUserName = (TextView) view.findViewById(R.id.tv_menu_username);
        rlExit = (RelativeLayout) view.findViewById(R.id.rl_menu_exit);

        rlExit.setOnClickListener(this);

        initView();
        return view;
    }

    private void initView() {
        tvUserName.setText(PersonalConstansts.userName);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_menu_exit:
                 new AlertDialog.Builder(getActivity())
                        .setMessage("确定退出程序吗?")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
        }
    }
}
