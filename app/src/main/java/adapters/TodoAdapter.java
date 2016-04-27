package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quantong.mobilefix.R;

import java.util.ArrayList;

import callbacks.RecyclerItemClickListener;
import databean.TodoListBean;

/**
 * Created by Cii on 2016/4/21.
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private RecyclerItemClickListener recyclerItemClickListener;
    private ArrayList<TodoListBean> beanList;

    public TodoAdapter(ArrayList<TodoListBean> list) {
        this.beanList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todolist, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvSN.setText(beanList.get(position).SN);
        holder.tvTitle.setText(beanList.get(position).title);
        holder.tvDetail.setText(beanList.get(position).detail);
        holder.ivState.setBackgroundResource(beanList.get(position).imageID);
        holder.tvDispatchTime.setText(beanList.get(position).dispatchTime);
        if (beanList.get(position).state.equals("待办")) {
            holder.tvExplain.setText(beanList.get(position).overTime);
        } else {
            holder.tvExplain.setText(beanList.get(position).state);
        }

        holder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerItemClickListener != null)
                    recyclerItemClickListener.onItemClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout llItem;
        public TextView tvSN;
        public ImageView ivState;
        public TextView tvExplain;
        public TextView tvTitle;
        public TextView tvDetail;
        public TextView tvDispatchTime;

        public ViewHolder(View view) {
            super(view);
            llItem = (LinearLayout) view.findViewById(R.id.ll_todolist);
            tvSN = (TextView) view.findViewById(R.id.tv_todolist_sn);
            ivState = (ImageView) view.findViewById(R.id.iv_todolist_state);
            tvExplain = (TextView) view.findViewById(R.id.tv_todolist_explain);
            tvTitle = (TextView) view.findViewById(R.id.tv_todolist_title);
            tvDetail = (TextView) view.findViewById(R.id.tv_todolist_detail);
            tvDispatchTime = (TextView) view.findViewById(R.id.tv_todolist_dispatchtime);
        }
    }

    public void setListener(RecyclerItemClickListener listener) {
        this.recyclerItemClickListener = listener;
    }
}
