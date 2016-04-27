package viewpagers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quantong.mobilefix.R;

import java.util.ArrayList;

import adapters.TodoAdapter;
import callbacks.RecyclerItemClickListener;
import databean.TodoListBean;
import equipmentrepair.EquipmentRepairActivity;

/**
 * Created by Cii on 2016/4/21.
 */
public class TodoFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rcvLayoutManager;
    private ArrayList<TodoListBean> beanList = new ArrayList<TodoListBean>();
    private TodoAdapter todoAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_todo);

        initData();

        rcvLayoutManager = new LinearLayoutManager(getActivity());
        todoAdapter = new TodoAdapter(beanList);
        recyclerView.setLayoutManager(rcvLayoutManager);
        //分割线
        //recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(todoAdapter);
        todoAdapter.setListener(new RecyclerItemClickListener() {
            @Override
            public void onItemClicked(int index) {
                Intent intent = new Intent(getActivity(), EquipmentRepairActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void initData() {
        for (int i = 0; i < 11; i++) {
            TodoListBean todoListBean = new TodoListBean();
            todoListBean.SN = "2025455879988-" + String.valueOf(i);
            todoListBean.state = "待办";
            todoListBean.imageID = R.drawable.iv_todolist_time;
            todoListBean.overTime = "3小时38分";
            todoListBean.title = "打印机需要维修";
            todoListBean.detail = "印机需要维修打印机需要维修打印机需要维修打印机需要维修打印机需要维修";
            todoListBean.dispatchTime = "2016-04-22 14:25:36";
            beanList.add(todoListBean);
        }
    }
}
