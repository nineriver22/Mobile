package activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.quantong.mobilefix.MainActivity;
import com.quantong.mobilefix.R;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

import Events.GetCodeEvent;
import callbacks.OkHttpCallBack;
import constants.PersonalConstansts;
import constants.ServiceContans;
import databeans.CheckTokenBean;
import databeans.GetCodeBean;
import databeans.LoginBean;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import uilts.OKHttpManager;

/**
 * Created by Constantine on 2016/4/22.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private final String TAG = "LoginActivity";
    private EditText etAccount;
    private EditText etPassword;
    private EditText etCode;
    private TextView tvGetCode;
    private TextView tvRemeberPassword;
    private TextView tvLogin;

    private int errorID = 200;
    private String code;
    private boolean isRemeberAccount = false;

    private String account;
    private String password;
    private String md5Password;
    private CheckTokenBean checkTokenBean;
    private GetCodeBean getCodeBean;
    private LoginBean loginBean;
    private LoginBean.DatumBean loginDatum;

    private int timerFlag;
    private Timer timer;
    private MyTimerTask myTimerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etAccount = (EditText) findViewById(R.id.et_login_account);
        etPassword = (EditText) findViewById(R.id.et_login_password);
        etCode = (EditText) findViewById(R.id.et_login_code);
        tvGetCode = (TextView) findViewById(R.id.tv_login_getcode);
        tvRemeberPassword = (TextView) findViewById(R.id.tv_login_remeberpsw);
        tvLogin = (TextView) findViewById(R.id.tv_login_login);

        if (isRemeberAccount) {
            tvRemeberPassword.setBackgroundResource(R.drawable.bg_login_remeberpsw);
        } else {
            tvRemeberPassword.setBackgroundResource(R.drawable.bg_login_remeberpsw_un);
        }

        tvGetCode.setOnClickListener(this);
        tvRemeberPassword.setOnClickListener(this);
        tvLogin.setOnClickListener(this);

//        startActivity(new Intent(this, MainActivity.class));
//        finish();
        EventBus.getDefault().register(this);

        defaultAccount();
    }

    private void defaultAccount() {
        SharedPreferences sp = getSharedPreferences(PersonalConstansts.FILENAME, MODE_PRIVATE);
        isRemeberAccount = sp.getBoolean(PersonalConstansts.REMEBERACCOUNT, false);
        String spAccount = sp.getString(PersonalConstansts.ACCOUNTKEY, "");
        String spPassword = sp.getString(PersonalConstansts.PASSWORDKEY, "");
        PersonalConstansts.token = sp.getString(PersonalConstansts.TOKEN, "");

        if (isRemeberAccount) {
            etAccount.setText(spAccount);
            etPassword.setText(spPassword);
            checkToken(PersonalConstansts.token);
            tvRemeberPassword.setBackgroundResource(R.drawable.bg_login_remeberpsw);
            Log.d(TAG, "defaultAccount: " + PersonalConstansts.token);
        }
    }

    private void checkToken(final String token) {
        if (!PersonalConstansts.token.equals("")) {
            RequestBody requestBody = new FormBody.Builder()
                    .add("token", token)
                    .add("t", String.valueOf(System.currentTimeMillis() / 1000L))
                    .build();

            Request request = new Request.Builder().post(requestBody).url(ServiceContans.checkToken).build();
            OKHttpManager.getInstance().enqueue(request, new OkHttpCallBack() {
                @Override
                public void onError(Call call, Exception e) {
                    Log.e(TAG, "onError: " + "error");
                }

                @Override
                public void onResponse(Object response) {
                    resolveCheckTokenData(response.toString());
                }
            });
        }
    }

    private void resolveCheckTokenData(final String json) {
        Gson gson = new Gson();
        try {
            checkTokenBean = gson.fromJson(json, CheckTokenBean.class);
            if (checkTokenBean.code == 200) {
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, checkTokenBean.message, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_remeberpsw:
                if (isRemeberAccount) {
                    tvRemeberPassword.setBackgroundResource(R.drawable.bg_login_remeberpsw_un);
                    isRemeberAccount = false;
                } else {
                    tvRemeberPassword.setBackgroundResource(R.drawable.bg_login_remeberpsw);
                    isRemeberAccount = true;
                }
                break;

            case R.id.tv_login_getcode:
                if (etAccount.getText().length() > 0 && etPassword.getText().length() > 0) {
                    etAccount.setText("wangqiaobian_cz");
                    account = "wangqiaobian_cz";
                    password = etPassword.getText().toString().trim();
                    md5Password = new String(Hex.encodeHex(DigestUtils.md5(password)));
                    getCode(account, md5Password);
                } else {
                    Toast.makeText(this, "请正确填写账户和密码!", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.tv_login_login:
                if (etAccount.getText().length() > 0 && etCode.getText().length() > 0) {
                    login(etAccount.getText().toString(), etCode.getText().toString());
                }
                break;
        }
    }

    private void getCode(String account, String password) {
        RequestBody requestBody = new FormBody.Builder()
                .add("username", account)
                .add("password", password)
                .add("t", String.valueOf(System.currentTimeMillis() / 1000L))
                .build();

        Request request = new Request.Builder().post(requestBody).url(ServiceContans.getCode).build();
        OKHttpManager.getInstance().enqueue(request, new OkHttpCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                Log.e(TAG, "onError: " + "error");
            }

            @Override
            public void onResponse(Object response) {
                resolveGetCodeData(response.toString());
            }
        });
    }

    private void resolveGetCodeData(String json) {
        Gson gson = new Gson();
        try {
            getCodeBean = gson.fromJson(json, GetCodeBean.class);
            if (getCodeBean.code == 200) {
                Toast.makeText(this, "验证码已开始发送,请注意查看您的手机!", Toast.LENGTH_SHORT).show();
                timerFlag = 10;
                timer = new Timer();
                myTimerTask = new MyTimerTask();
                timer.schedule(myTimerTask, 1000, 1000);
            } else {
                Toast.makeText(this, getCodeBean.message, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    private void login(final String account, final String code) {
        RequestBody requestBody = new FormBody.Builder()
                .add("username", account)
                .add("smsCode", code)
                .add("t", String.valueOf(System.currentTimeMillis() / 1000L))
                .build();

        Request request = new Request.Builder().post(requestBody).url(ServiceContans.login).build();
        OKHttpManager.getInstance().enqueue(request, new OkHttpCallBack() {
            @Override
            public void onError(Call call, Exception e) {
                Log.e(TAG, "onError: " + "error");
            }

            @Override
            public void onResponse(Object response) {
                resolveLoginData(response.toString());
            }
        });
    }

    private void resolveLoginData(String json) {
        Gson gson = new Gson();
        try {
            loginBean = gson.fromJson(json, LoginBean.class);
            if (loginBean.code == 200) {
                loginDatum = loginBean.datum;
                PersonalConstansts.token = loginDatum.token;
                PersonalConstansts.identityType = loginDatum.type;
                if (isRemeberAccount) {
                    saveAccount(account, password, PersonalConstansts.token);
                }
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, loginBean.message, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    private void saveAccount(final String account, final String password, final String token) {
        SharedPreferences sp = getSharedPreferences(PersonalConstansts.FILENAME, MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit();
        spEditor.putBoolean(PersonalConstansts.REMEBERACCOUNT, isRemeberAccount);
        spEditor.putString(PersonalConstansts.ACCOUNTKEY, account);
        spEditor.putString(PersonalConstansts.PASSWORDKEY, password);
        spEditor.putString(PersonalConstansts.TOKEN, token);
        spEditor.apply();
    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            if (timerFlag > 0) {
                EventBus.getDefault().post(new GetCodeEvent(timerFlag));
                timerFlag = timerFlag - 1;
            } else {
                EventBus.getDefault().post(new GetCodeEvent(0));
                timer.cancel();
                if (myTimerTask != null) {
                    myTimerTask.cancel();  //将原任务从队列中移除
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setGetCodeEnable(GetCodeEvent getCodeEvent) {
        if (getCodeEvent.timeFlag == 0) {
            if (myTimerTask != null) {
                myTimerTask.cancel();
            }
            tvGetCode.setEnabled(true);
            tvGetCode.setText("获取验证码");
        } else {
            tvGetCode.setEnabled(false);
            tvGetCode.setText(String.valueOf(getCodeEvent.timeFlag));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (myTimerTask != null)
            myTimerTask.cancel();
        Log.d(TAG, "onDestroy: ");
    }

}
