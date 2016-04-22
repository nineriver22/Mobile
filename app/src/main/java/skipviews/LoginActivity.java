package skipviews;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.quantong.mobilefix.MainActivity;
import com.quantong.mobilefix.R;

import constants.PersonalConstansts;

/**
 * Created by Cii on 2016/4/22.
 */
public class LoginActivity extends Activity implements View.OnClickListener {

    private EditText etAccount;
    private EditText etPassword;
    private Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etAccount = (EditText) findViewById(R.id.et_login_account);
        etPassword = (EditText) findViewById(R.id.et_login_password);
        btLogin = (Button) findViewById(R.id.bt_login_login);

        btLogin.setOnClickListener(this);
    }

    private void defaultLogin() {
        SharedPreferences sp = getSharedPreferences(PersonalConstansts.FILENAME, MODE_PRIVATE);
        String spAccount = sp.getString(PersonalConstansts.ACCOUNTKEY, "");
        String spPassword = sp.getString(PersonalConstansts.PASSWORDKEY, "");
        if (!spAccount.equals("") && !spPassword.equals("") && !PersonalConstansts.isChangeAccount) {
            etAccount.setText(spAccount);
            etPassword.setText(spPassword);
            login(spAccount, spPassword);
        }
    }

    private void login(String account, String password) {
        if (account.equals("1") && password.equals("1")) {
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
            case R.id.bt_login_login:
                if (etAccount.getText().toString().length() > 0 && etPassword.getText().toString().length() > 0) {
                    login(etAccount.getText().toString(), etPassword.getText().toString());
                }
                break;
        }
    }
}
