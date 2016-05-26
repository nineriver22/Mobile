package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.quantong.mobilefix.R;

/**
 * Created by Constantine on 2016/4/28.
 */
public class EvaluateFragment extends Fragment implements View.OnClickListener {

    private final String TAG = "EvaluateFragment";

    private ImageView ivStar1;
    private ImageView ivStar2;
    private ImageView ivStar3;
    private ImageView ivStar4;
    private ImageView ivStar5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluate, container, false);
        ivStar1 = (ImageView) view.findViewById(R.id.iv_evaluate_star1);
        ivStar2 = (ImageView) view.findViewById(R.id.iv_evaluate_star2);
        ivStar3 = (ImageView) view.findViewById(R.id.iv_evaluate_star3);
        ivStar4 = (ImageView) view.findViewById(R.id.iv_evaluate_star4);
        ivStar5 = (ImageView) view.findViewById(R.id.iv_evaluate_star5);

        ivStar1.setOnClickListener(this);
        ivStar2.setOnClickListener(this);
        ivStar3.setOnClickListener(this);
        ivStar4.setOnClickListener(this);
        ivStar5.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.iv_evaluate_star1:
//                ivStar1.setBackgroundResource(R.drawable.iv_evaluate_lightstar);
//                ivStar2.setBackgroundResource(R.drawable.iv_evaluate_darkstar);
//                ivStar3.setBackgroundResource(R.drawable.iv_evaluate_darkstar);
//                ivStar4.setBackgroundResource(R.drawable.iv_evaluate_darkstar);
//                ivStar5.setBackgroundResource(R.drawable.iv_evaluate_darkstar);
//                break;
//            case R.id.iv_evaluate_star2:
//                ivStar1.setBackgroundResource(R.drawable.iv_evaluate_lightstar);
//                ivStar2.setBackgroundResource(R.drawable.iv_evaluate_lightstar);
//                ivStar3.setBackgroundResource(R.drawable.iv_evaluate_darkstar);
//                ivStar4.setBackgroundResource(R.drawable.iv_evaluate_darkstar);
//                ivStar5.setBackgroundResource(R.drawable.iv_evaluate_darkstar);
//                break;
//            case R.id.iv_evaluate_star3:
//                ivStar1.setBackgroundResource(R.drawable.iv_evaluate_lightstar);
//                ivStar2.setBackgroundResource(R.drawable.iv_evaluate_lightstar);
//                ivStar3.setBackgroundResource(R.drawable.iv_evaluate_lightstar);
//                ivStar4.setBackgroundResource(R.drawable.iv_evaluate_darkstar);
//                ivStar5.setBackgroundResource(R.drawable.iv_evaluate_darkstar);
//                break;
//            case R.id.iv_evaluate_star4:
//                ivStar1.setBackgroundResource(R.drawable.iv_evaluate_lightstar);
//                ivStar2.setBackgroundResource(R.drawable.iv_evaluate_lightstar);
//                ivStar3.setBackgroundResource(R.drawable.iv_evaluate_lightstar);
//                ivStar4.setBackgroundResource(R.drawable.iv_evaluate_lightstar);
//                ivStar5.setBackgroundResource(R.drawable.iv_evaluate_darkstar);
//                break;
//            case R.id.iv_evaluate_star5:
//                ivStar1.setBackgroundResource(R.drawable.iv_evaluate_lightstar);
//                ivStar2.setBackgroundResource(R.drawable.iv_evaluate_lightstar);
//                ivStar3.setBackgroundResource(R.drawable.iv_evaluate_lightstar);
//                ivStar4.setBackgroundResource(R.drawable.iv_evaluate_lightstar);
//                ivStar5.setBackgroundResource(R.drawable.iv_evaluate_lightstar);
//                break;
//        }
    }
}
