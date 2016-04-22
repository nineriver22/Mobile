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
import databean.TodoBean;

/**
 * Created by Cii on 2016/4/21.
 */
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    private RecyclerItemClickListener recyclerItemClickListener;
    private ArrayList<TodoBean> todoBeanList;

    public TodoAdapter(ArrayList<TodoBean> list) {
        this.todoBeanList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTitle.setText(todoBeanList.get(position).listTitle);
        holder.tvContent.setText(todoBeanList.get(position).listContent);
        holder.tvLevel.setText(todoBeanList.get(position).level);
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
        return todoBeanList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout llItem;
        public ImageView ivEquipment;
        public ImageView ivLevel;
        public TextView tvTitle;
        public TextView tvLevel;
        public TextView tvContent;

        public ViewHolder(View view) {
            super(view);
            llItem = (LinearLayout) view.findViewById(R.id.ll_itemtodo);
            ivEquipment = (ImageView) view.findViewById(R.id.iv_itemtodo_equipment);
            ivLevel = (ImageView) view.findViewById(R.id.iv_itemtodo_level);
            tvTitle = (TextView) view.findViewById(R.id.tv_itemtodo_listtitle);
            tvLevel = (TextView) view.findViewById(R.id.tv_itemtodo_level);
            tvContent = (TextView) view.findViewById(R.id.tv_itemtodo_listcontent);
        }
    }

    public void setListener(RecyclerItemClickListener listener) {
        this.recyclerItemClickListener = listener;
    }
}
