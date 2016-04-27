package viewpagers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.quantong.mobilefix.R;

import activities.EquRepListActivity;

/**
 * Created by Cii on 2016/4/21.
 */
public class RepairFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "RepairFragment";

    private ImageButton ibtnEquipmentRepair;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repair, container, false);
        ibtnEquipmentRepair = (ImageButton) view.findViewById(R.id.ibtn_repair_equrep);

        ibtnEquipmentRepair.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ibtn_repair_equrep:
                Intent eqReListIntent = new Intent(getActivity(), EquRepListActivity.class);
                eqReListIntent.putExtra("titleKey", "故障报修");
                startActivity(eqReListIntent);
                break;
        }
    }
}
