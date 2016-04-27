package activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.quantong.mobilefix.MainActivity;
import com.quantong.mobilefix.R;

import constants.PersonalConstansts;

/**
 * Created by Cii on 2016/4/22.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText etAccount;
    private EditText etPassword;
    private EditText etCode;
    private TextView tvRememberPassword;
    private TextView tvGetCode;
    private TextView tvLogin;

    private String account;
    private String password;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etAccount = (EditText) findViewById(R.id.et_login_account);
        etPassword = (EditText) findViewById(R.id.et_login_password);
        etCode = (EditText) findViewById(R.id.et_login_code);
        tvLogin = (TextView) findViewById(R.id.tv_login_login);

        tvLogin.setOnClickListener(this);

//        startActivity(new Intent(this, MainActivity.class));
//        finish();
    }

    private void defaultAccount() {
        SharedPreferences sp = getSharedPreferences(PersonalConstansts.FILENAME, MODE_PRIVATE);
        String spAccount = sp.getString(PersonalConstansts.ACCOUNTKEY, "");
        String spPassword = sp.getString(PersonalConstansts.PASSWORDKEY, "");
        if (!spAccount.equals("") && !spPassword.equals("") && !PersonalConstansts.isChangeAccount) {
            etAccount.setText(spAccount);
            etPassword.setText(spPassword);
        }
    }

    private void login(String account, String password, String code) {
        if (account.equals("1") && password.equals("1") && code.equals("1")) {
            //saveAccount(account, password);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {

        }
    }

    private void saveAccount(String account, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences(PersonalConstansts.FILENAME, MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sharedPreferences.edit();
        spEditor.putString("account", account);
        spEditor.putString("password", password);
        spEditor.apply();
        PersonalConstansts.account = account;
        PersonalConstansts.password = password;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_login:
                account = etAccount.getText().toString();
                password = etPassword.getText().toString();
                code = etCode.getText().toString();
                if (account.length() > 0 && password.length() > 0 && code.length() > 0) {
                    login(account, password, code);
                }
                break;
        }
    }
}
