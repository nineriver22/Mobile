package activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.quantong.mobilefix.R;

/**
 * Created by Constantine on 2016/5/26.
 */
public class SearchActivity extends Activity implements View.OnClickListener {

    private final String TAG = "SearchActivity";

    private ImageView ivBack;
    private LinearLayout llSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ivBack = (ImageView) findViewById(R.id.iv_search_back);
        llSearch = (LinearLayout) findViewById(R.id.ll_search);

        ivBack.setOnClickListener(this);
        llSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_back:
                finish();
                break;
            case R.id.ll_search:

                break;
        }
    }
}
