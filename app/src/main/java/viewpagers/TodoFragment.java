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
import databean.TodoBean;
import equipmentrepair.EquipmentRepairActivity;
import uilts.DividerItemDecoration;

/**
 * Created by Cii on 2016/4/21.
 */
public class TodoFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager rcvLayoutManager;
    private ArrayList<TodoBean> todoBeanList = new ArrayList<TodoBean>();
    private TodoAdapter todoAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rcv_todo);

        for (int i = 0; i < 11; i++) {
            TodoBean todoBean = new TodoBean();
            todoBean.listTitle = "标题" + String.valueOf(i);
            todoBean.listContent = "内容" + String.valueOf(i);
            todoBean.level = String.valueOf(i);
            todoBeanList.add(todoBean);
        }
        rcvLayoutManager = new LinearLayoutManager(getActivity());
        todoAdapter = new TodoAdapter(todoBeanList);
        recyclerView.setLayoutManager(rcvLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
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
}
