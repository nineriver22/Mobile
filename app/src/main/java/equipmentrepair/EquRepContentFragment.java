package equipmentrepair;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quantong.mobilefix.R;

/**
 * Created by Cii on 2016/4/26.
 */
public class EquRepContentFragment extends Fragment {

    private final String TAG = "EquRepContentFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_equrep_content, container, false);
        return view;
    }
}
