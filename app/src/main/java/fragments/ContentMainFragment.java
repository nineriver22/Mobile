package fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quantong.mobilefix.R;

import org.greenrobot.eventbus.EventBus;

import Events.MenuEvent;
import constants.PersonalConstansts;

/**
 * Created by Constantine on 2016/5/6.
 */
public class ContentMainFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "ContentMainFragment";
    private final static int SCAN_REQUEST_CODE = 1;

    private TextView tvToRepairCount;
    private TextView tvRepairingCount;
    private TextView tvRepairedCount;
    private EditText etSearch;
    private ImageView ivMenu;
    private ImageView ivScan;
    private LinearLayout llReportor;
    private LinearLayout llOperator;
    private LinearLayout llEvaluate;
    private LinearLayout llService;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        tvToRepairCount = (TextView) view.findViewById(R.id.tv_contentmain_torepaircount);
        tvRepairingCount = (TextView) view.findViewById(R.id.tv_contentmain_repairingcount);
        tvRepairedCount = (TextView) view.findViewById(R.id.tv_contentmain_repairedcount);
        etSearch = (EditText) view.findViewById(R.id.et_contentmain_search);
        ivMenu = (ImageView) view.findViewById(R.id.iv_contentmain_menu);
        ivScan = (ImageView) view.findViewById(R.id.iv_contentmain_scan);
        llReportor = (LinearLayout) view.findViewById(R.id.ll_contentmain_reportor);
        llOperator = (LinearLayout) view.findViewById(R.id.ll_contentmain_operator);
        llService = (LinearLayout) view.findViewById(R.id.ll_contentmain_service);
        llService = (LinearLayout) view.findViewById(R.id.ll_contentmain_service);

        ivMenu.setOnClickListener(this);
        ivScan.setOnClickListener(this);
        initView();
        return view;
    }

    private void initView() {
        if (PersonalConstansts.AUTYPE.equals("yyt")) {
            llReportor.setVisibility(View.VISIBLE);
            llOperator.setVisibility(View.GONE);
            llService.setVisibility(View.GONE);
            llEvaluate.setVisibility(View.VISIBLE);
        }
        if (PersonalConstansts.AUTYPE.equals("dw")) {
            llReportor.setVisibility(View.GONE);
            llOperator.setVisibility(View.VISIBLE);
            llService.setVisibility(View.VISIBLE);
            llEvaluate.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_contentmain_menu:
                EventBus.getDefault().post(new MenuEvent());
                break;
//            case R.id.ibtn_contentmain_scan:
//                Intent intent = new Intent(getActivity(), ScanActivity.class);
//                startActivityForResult(intent, SCAN_REQUEST_CODE);
//                break;
        }
    }

    //if have nested fragment, the called activity's lauchmode must be set standard,or else onActivityResult is useless
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCAN_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    Log.d(TAG, "onActivityResult: " + bundle.getString("result"));
                    etSearch.setText(bundle.getString("result"));
                }
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
