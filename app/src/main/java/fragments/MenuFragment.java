package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.quantong.mobilefix.R;


/**
 * Created by cii on 2016/1/5.
 */
public class MenuFragment extends Fragment implements OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu, container, false);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
