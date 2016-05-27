package activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.quantong.mobilefix.R;

import java.util.ArrayList;
import java.util.List;

import callbacks.OkHttpCallBack;
import constants.PersonalConstansts;
import constants.ServiceContans;
import databeans.SearchBillsBean;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import uilts.OKHttpManager;
import uilts.TimeoutDeal;

/**
 * Created by Constantine on 2016/5/26.
 */
public class SearchBillsActivity extends Activity implements View.OnClickListener {

    private final String TAG = "SearchBillsActivity";

    private EditText etSearchSN;
    private ImageView ivBack;
    private LinearLayout llSearch;
    private RecyclerView rcvSearch;
    private RecyclerView.LayoutManager layoutManager;

    private SearchAdapter searchAdapter;
    private SearchBillsBean searchBillsBean;
    private List<SearchBillsBean.DatumBean.ListBean> billsList;

    private int sumRow = 0;
    private int pageNo = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbills);

        etSearchSN = (EditText) findViewById(R.id.et_searchbills_sn);
        ivBack = (ImageView) findViewById(R.id.iv_searchbills_back);
        llSearch = (LinearLayout) findViewById(R.id.ll_searchbills);
        rcvSearch = (RecyclerView) findViewById(R.id.rcv_searchbills);

        searchAdapter = new SearchAdapter();
        rcvSearch.setAdapter(searchAdapter);
        layoutManager = new LinearLayoutManager(this);
        rcvSearch.setLayoutManager(layoutManager);

        billsList = new ArrayList<SearchBillsBean.DatumBean.ListBean>();

        initControl();
    }

    private void initControl() {
        ivBack.setOnClickListener(this);
        llSearch.setOnClickListener(this);
        etSearchSN.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int searchLength = etSearchSN.getText().toString().trim().length();
                if (searchLength > 0) {
                    llSearch.setEnabled(true);
                    llSearch.setBackgroundResource(R.color.red);
                } else {
                    llSearch.setEnabled(false);
                    llSearch.setBackgroundResource(R.color.line);
                }
            }
        });
        rcvSearch.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                    int totalItemCount = linearLayoutManager.getItemCount();

                    if (lastVisibleItem == totalItemCount - 2 && totalItemCount < sumRow) {
                        getSearchBills(etSearchSN.getText().toString().trim(), pageNo, PersonalConstansts.PAGESIZE);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_searchbills_back:
                finish();
                break;
            case R.id.ll_searchbills:
                billsList.clear();
                searchAdapter.notifyDataSetChanged();
                getSearchBills(etSearchSN.getText().toString().trim(), pageNo, PersonalConstansts.PAGESIZE);
                break;
        }
    }

    private void getSearchBills(final String SN, final int pageNo, final int pageSize) {
        RequestBody requestBody = new FormBody.Builder()
                .add("code", SN)
                .add("pageNo", String.valueOf(pageNo))
                .add("pageSize", String.valueOf(pageSize))
                .add("token", PersonalConstansts.token)
                .add("t", String.valueOf(System.currentTimeMillis() / 1000L))
                .build();

        Request request = new Request.Builder().post(requestBody).url(ServiceContans.searchBills).build();
        OKHttpManager.getInstance().enqueue(request, new OkHttpCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                Log.e(TAG, "onError: " + "error");
            }

            @Override
            public void onResponse(Object response) {
                resolveSearchBillsData(response.toString());
            }
        });
    }

    private void resolveSearchBillsData(String json) {
        Log.d(TAG, "resolveSearchBillsData: " + json);
        Gson gson = new Gson();
        try {
            searchBillsBean = gson.fromJson(json, SearchBillsBean.class);
            if (searchBillsBean.code == 200) {
                billsList.addAll(searchBillsBean.datum.list);
                sumRow = searchBillsBean.datum.totalRow;
                searchAdapter.notifyDataSetChanged();
                pageNo = pageNo + 1;
            } else if (searchBillsBean.code == 401) {
                TimeoutDeal.overTime(getApplication());
            } else {
                Toast.makeText(this, searchBillsBean.message, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    private class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(SearchBillsActivity.this).
                    inflate(R.layout.item_repair, parent, false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (PersonalConstansts.AUTYPE.equals("dw") && billsList.get(position).treat_timeout) {
                holder.ivIimeSign.setImageResource(R.drawable.iv_sign_overtime);
            }
            holder.tvTitle.setText(billsList.get(position).sub_category_name);
            holder.tvSchedule.setText(String.valueOf(billsList.get(position).task_name));
            holder.tvSN.setText(billsList.get(position).bill_id);
            holder.tvDescription.setText(billsList.get(position).hitch_des);
            holder.tvCreateTime.setText(billsList.get(position).create_time);
        }

        @Override
        public int getItemCount() {
            return billsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            public LinearLayout llContent;
            public ImageView ivIimeSign;
            public TextView tvTitle;
            public TextView tvSchedule;
            public TextView tvSN;
            public TextView tvDescription;
            public TextView tvCreateTime;
            public TextView tvEvaluate;
            public TextView tvFittingInfo;

            public ViewHolder(View view) {
                super(view);
                llContent = (LinearLayout) view.findViewById(R.id.ll_itemrepair);
                ivIimeSign = (ImageView) view.findViewById(R.id.iv_itemrepair_timesign);
                tvTitle = (TextView) view.findViewById(R.id.tv_itemrepair_title);
                tvSchedule = (TextView) view.findViewById(R.id.tv_itemrepair_schedule);
                tvSN = (TextView) view.findViewById(R.id.tv_itemrepair_sn);
                tvDescription = (TextView) view.findViewById(R.id.tv_itemrepair_description);
                tvCreateTime = (TextView) view.findViewById(R.id.tv_itemrepair_createtime);
                tvEvaluate = (TextView) view.findViewById(R.id.tv_itemrepair_evaluate);
                tvFittingInfo = (TextView) view.findViewById(R.id.tv_itemrepair_fittinginfo);
            }
        }
    }

}
