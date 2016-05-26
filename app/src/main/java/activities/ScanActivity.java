package activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Constantine on 2016/4/29.
 */
public class ScanActivity extends Activity implements View.OnClickListener, ZXingScannerView.ResultHandler {

    private final String TAG = "ScanActivity";
    private ZXingScannerView zXingScannerView;

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zXingScannerView = new ZXingScannerView(this);
        setContentView(zXingScannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        if (!result.getText().equals("")) {
            Log.d(TAG, "handleResult: " + result.getText());
            Intent resultIntent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("result", result.getText());
            resultIntent.putExtras(bundle);
            this.setResult(RESULT_OK, resultIntent);
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

}
