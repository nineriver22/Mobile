package activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.quantong.mobilefix.R;

/**
 * Created by Cii on 2016/4/22.
 */
public class CommonActivity extends FragmentActivity implements View.OnClickListener {

    public static String listID;

    private ImageView ivBack;
    private TextView tvTitle;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        ivBack = (ImageView) findViewById(R.id.iv_common_back);
        tvTitle = (TextView) findViewById(R.id.tv_common_title);

        fragmentManager = getSupportFragmentManager();

        ivBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_common_back:
                finish();
                break;
        }
    }
}
