package activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.quantong.mobilefix.R;

import java.util.ArrayList;

import adapters.TodoAdapter;
import databeans.TodoListBean;

/**
 * Created by Constantine on 2016/4/27.
 */
public class EquRepListActivity extends Activity implements View.OnClickListener {

    private final String TAG = "EquRepListActivity";

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rcvLayoutManager;
    private ImageView ivBack;
    private ImageView ivScan;
    private TextView tvTitle;
    private TextView tvTodoList;
    private TextView tvDoneList;
    private View vTodoList;
    private View vDoneList;

    private ArrayList<TodoListBean> allList = new ArrayList<TodoListBean>();
    private ArrayList<TodoListBean> todoList = new ArrayList<TodoListBean>();
    private ArrayList<TodoListBean> doneList = new ArrayList<TodoListBean>();
    private TodoAdapter todoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equreplist);
        recyclerView = (RecyclerView) findViewById(R.id.rcv_equreplist);


        ivBack = (ImageView) findViewById(R.id.iv_equreplist_back);
        ivScan = (ImageView) findViewById(R.id.iv_equreplist_scan);
        tvTitle = (TextView) findViewById(R.id.tv_equreplist_title);
        tvTodoList = (TextView) findViewById(R.id.tv_equreplist_todolist);
        tvDoneList = (TextView) findViewById(R.id.tv_equreplist_donelist);
        vTodoList = (View) findViewById(R.id.view_equreplist_todolist);
        vDoneList = (View) findViewById(R.id.view_equreplist_donelist);

        Intent intent = getIntent();
        tvTitle.setText(intent.getStringExtra("titleKey"));
        initData();
        rcvLayoutManager = new LinearLayoutManager(this);
        todoAdapter = new TodoAdapter(allList);
        recyclerView.setLayoutManager(rcvLayoutManager);
        //添加分割线
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(todoAdapter);

        ivBack.setOnClickListener(this);
        ivScan.setOnClickListener(this);
        tvTodoList.setOnClickListener(this);
        tvDoneList.setOnClickListener(this);
    }

    private void initData() {
        for (int i = 0; i < 11; i++) {
            TodoListBean todoListBean = new TodoListBean();
            todoListBean.SN = "2025455879988-" + String.valueOf(i);
            todoListBean.state = "待办";
            //todoListBean.imageID = R.drawable.iv_todolist_time;
            todoListBean.overTime = "3小时38分";
            todoListBean.title = "打印机需要维修";
            todoListBean.detail = "印机需要维修打印机需要维修打印机需要维修打印机需要维修打印机需要维修";
            todoListBean.dispatchTime = "2016-04-22 14:25:36";
            todoList.add(todoListBean);

            TodoListBean doneListBean = new TodoListBean();
            doneListBean.SN = "xassddeeesaw-" + String.valueOf(i);
            doneListBean.state = "已完成";
            //doneListBean.imageID = R.drawable.iv_todolist_done;
            doneListBean.overTime = "3小时38分";
            doneListBean.title = "已完成";
            doneListBean.detail = "已完成已完成已完成已完成已完成已完成已完成已完成已完成已完成";
            doneListBean.dispatchTime = "2016-04-22 14:25:36";
            doneList.add(doneListBean);
        }
        allList.addAll(todoList);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_equreplist_back:
                finish();
                break;
            case R.id.iv_equreplist_scan:
                startActivity(new Intent(this,ScanActivity.class));
                break;
            case R.id.tv_equreplist_todolist:
                selectedEffect(tvTodoList, vTodoList);
                changeData(todoList);
                break;
            case R.id.tv_equreplist_donelist:
                selectedEffect(tvDoneList, vDoneList);
                changeData(doneList);
                break;
        }
    }

    private void selectedEffect(TextView textView, View view) {
        tvTodoList.setTextColor(getResources().getColor(R.color.primary_text));
        tvDoneList.setTextColor(getResources().getColor(R.color.primary_text));
        vTodoList.setBackgroundColor(getResources().getColor(R.color.white));
        vDoneList.setBackgroundColor(getResources().getColor(R.color.white));

        textView.setTextColor(getResources().getColor(R.color.bottom_light));
        view.setBackgroundColor(getResources().getColor(R.color.bottom_light));
    }

    private void changeData(ArrayList<TodoListBean> resourceList) {
        allList.clear();
        allList.addAll(resourceList);
        todoAdapter.notifyDataSetChanged();
    }
}
