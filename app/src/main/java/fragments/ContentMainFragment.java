package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.quantong.mobilefix.R;

import org.greenrobot.eventbus.EventBus;

import Events.MenuEvent;
import activities.MyRepairActivity;
import activities.SearchBillsActivity;
import callbacks.OkHttpCallBack;
import constants.PersonalConstansts;
import constants.ServiceContans;
import databeans.FlagCountBean;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import uilts.OKHttpManager;

/**
 * Created by Constantine on 2016/5/6.
 */
public class ContentMainFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "ContentMainFragment";
    private final static int SCAN_REQUEST_CODE = 1;

    private TextView tvToRepairCount;
    private TextView tvRepairingCount;
    private TextView tvRepairedCount;
    private TextView tvSearch;
    private ImageView ivMenu;
    private ImageView ivScan;
    private LinearLayout llReportor;
    private LinearLayout llOperator;
    private LinearLayout llEvaluate;
    private LinearLayout llService;

    private FlagCountBean flagCountBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        tvToRepairCount = (TextView) view.findViewById(R.id.tv_contentmain_torepaircount);
        tvRepairingCount = (TextView) view.findViewById(R.id.tv_contentmain_repairingcount);
        tvRepairedCount = (TextView) view.findViewById(R.id.tv_contentmain_repairedcount);
        tvSearch = (TextView) view.findViewById(R.id.tv_contentmain_search);
        ivMenu = (ImageView) view.findViewById(R.id.iv_contentmain_menu);
        ivScan = (ImageView) view.findViewById(R.id.iv_contentmain_scan);
        llReportor = (LinearLayout) view.findViewById(R.id.ll_contentmain_reportor);
        llOperator = (LinearLayout) view.findViewById(R.id.ll_contentmain_operator);
        llService = (LinearLayout) view.findViewById(R.id.ll_contentmain_service);
        llService = (LinearLayout) view.findViewById(R.id.ll_contentmain_service);
        LinearLayout llRepair = (LinearLayout) view.findViewById(R.id.ll_contentmain_torepair);
        LinearLayout llRepairing = (LinearLayout) view.findViewById(R.id.ll_contentmain_repairing);
        LinearLayout llRepaired = (LinearLayout) view.findViewById(R.id.ll_contentmain_repaired);

        ivMenu.setOnClickListener(this);
        tvSearch.setOnClickListener(this);
        ivScan.setOnClickListener(this);
        llRepair.setOnClickListener(this);
        llRepairing.setOnClickListener(this);
        llRepaired.setOnClickListener(this);

        initView();

        getFlagCount();
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

    private void getFlagCount() {
        RequestBody requestBody = new FormBody.Builder()
                .add("token", PersonalConstansts.token)
                .add("t", String.valueOf(System.currentTimeMillis() / 1000L))
                .build();

        Request request = new Request.Builder().post(requestBody).url(ServiceContans.getFlagCount).build();
        OKHttpManager.getInstance().enqueue(request, new OkHttpCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                Log.e(TAG, "onError: " + "error");
            }

            @Override
            public void onResponse(Object response) {
                resolveFlagCountData(response.toString());
            }
        });
    }

    private void resolveFlagCountData(String json) {
        Gson gson = new Gson();
        try {
            flagCountBean = gson.fromJson(json, FlagCountBean.class);
            if (flagCountBean.code == 200) {
                tvToRepairCount.setText(String.valueOf(flagCountBean.datum.toRepir));
                tvRepairingCount.setText(String.valueOf(flagCountBean.datum.repiring));
                tvRepairedCount.setText(String.valueOf(flagCountBean.datum.repired));
            } else {
                Toast.makeText(getActivity(), flagCountBean.message, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_contentmain_menu:
                EventBus.getDefault().post(new MenuEvent());
                break;
            case R.id.tv_contentmain_search:
                startActivity(new Intent(getActivity(), SearchBillsActivity.class));
                break;
//            case R.id.ibtn_contentmain_scan:
//                Intent intent = new Intent(getActivity(), ScanActivity.class);
//                startActivityForResult(intent, SCAN_REQUEST_CODE);
//                break;
            case R.id.ll_contentmain_torepair:
                Intent goToRepair = new Intent(getActivity(), MyRepairActivity.class);
                goToRepair.putExtra(MyRepairActivity.TAB, 0);
                startActivity(goToRepair);
                break;
            case R.id.ll_contentmain_repairing:
                Intent goRepairing = new Intent(getActivity(), MyRepairActivity.class);
                goRepairing.putExtra(MyRepairActivity.TAB, 1);
                startActivity(goRepairing);
                break;
            case R.id.ll_contentmain_repaired:
                Intent goRepaired = new Intent(getActivity(), MyRepairActivity.class);
                goRepaired.putExtra(MyRepairActivity.TAB, 2);
                startActivity(goRepaired);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
