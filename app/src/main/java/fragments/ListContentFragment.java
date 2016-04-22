package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quantong.mobilefix.R;

import databean.TodoBean;
import skipviews.CommonActivity;

/**
 * Created by Cii on 2016/4/21.
 */
public class ListContentFragment extends Fragment {

    private final String TAG = "ListContentFragment";
    private TodoBean todoBean;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listcontent, container, false);
        Log.d(TAG, CommonActivity.listID);
        return view;
    }
}
