package equipmentrepair;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.quantong.mobilefix.R;

import databean.TodoBean;

/**
 * Created by Cii on 2016/4/21.
 */
public class EquipmentRepairActivity extends FragmentActivity implements View.OnClickListener {

    private final String TAG = "EquipmentRepairActivity";
    private ImageView ivBack;
    private ImageButton ibtnContent;
    private ImageButton ibtnBackup;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment fragmentShowing;

    private Fragment mEquRepContentFragment;
    private Fragment mEquRepBackupFragment;
    private TodoBean todoBean;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipmentrepair);
        ivBack = (ImageView) findViewById(R.id.iv_equipmentrepair_back);
        ibtnContent = (ImageButton) findViewById(R.id.ibtn_equipmentrepair_content);
        ibtnBackup = (ImageButton) findViewById(R.id.ibtn_equipmentrepair_backup);

        fragmentManager = getSupportFragmentManager();
        mEquRepContentFragment = new EquRepContentFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_equipmentrepair, mEquRepContentFragment).commit();
        fragmentShowing = mEquRepContentFragment;

        ivBack.setOnClickListener(this);
        ibtnContent.setOnClickListener(this);
        ibtnBackup.setOnClickListener(this);
    }

    private void switchContent(Fragment fromFragment, Fragment toFragment) {
        if (fragmentShowing != toFragment) {
            fragmentTransaction = fragmentManager.beginTransaction();
            if (!toFragment.isAdded()) {
                fragmentTransaction.hide(fromFragment).add(R.id.fl_equipmentrepair, toFragment).commit();
            } else {
                fragmentTransaction.hide(fragmentShowing).show(toFragment).commit();
            }
            fragmentShowing = toFragment;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_equipmentrepair_back:
                finish();
                break;
            case R.id.ibtn_equipmentrepair_content:
                switchContent(fragmentShowing, mEquRepContentFragment);
                break;
            case R.id.ibtn_equipmentrepair_backup:
                if (mEquRepBackupFragment == null) {
                    mEquRepBackupFragment = new EquRepBackupFragment();
                }
                switchContent(fragmentShowing, mEquRepBackupFragment);
                break;
        }
    }
}
