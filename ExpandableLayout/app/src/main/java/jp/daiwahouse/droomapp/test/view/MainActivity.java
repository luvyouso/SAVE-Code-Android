package jp.daiwahouse.droomapp.test.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import jp.co.geniee.sdk.messaging.GenieeMessaging;
import jp.daiwahouse.droomapp.test.R;
import jp.daiwahouse.droomapp.test.databinding.ActivityMainBinding;

/**
 * Created by nguyentu on 5/24/17.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = "MainActivity";
    private ActivityMainBinding mMainBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mMainBinding.accountTitle.setOnClickListener(this);
        mMainBinding.certificateTitle.setOnClickListener(this);
        mMainBinding.registerTitle.setOnClickListener(this);
        mMainBinding.authenticationTitle.setOnClickListener(this);
        mMainBinding.homeTitle.setOnClickListener(this);

        mMainBinding.certificateLogin.setOnClickListener(this);
        initMajin();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void initMajin() {
        String registrationId = GenieeMessaging.getRegistrationId();
        GenieeMessaging.setNotificationsEnabled(true);

        if (registrationId == null || registrationId.isEmpty()) {
            // IDがない
            Toast.makeText(MainActivity.this, "RegistrationIDが取得できません", Toast.LENGTH_SHORT).show();
            Log.i(TAG, "RegistrationIDが取得できません");
            Log.i(TAG, "------------------------------------");
            return;
        }

        Toast.makeText(MainActivity.this, "RegistrationIDを取得しました。ID: " + registrationId, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "RegistrationIDを取得しました。ID: " + registrationId);
        Log.i(TAG, "------------------------------------");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.accountTitle:
                mMainBinding.expandableLayout.toggle();
                break;
            case R.id.certificateTitle:
                mMainBinding.expandablePanelCertificate.toggle();
                break;
            case R.id.registerTitle:
                mMainBinding.expandablePanelRegister.toggle();
                break;
            case R.id.authenticationTitle:
                mMainBinding.expandablePanelAuthentication.toggle();
                break;
            case R.id.homeTitle:
                mMainBinding.expandablePanelHome.toggle();
                break;
            case R.id.certificateLogin:
                startActivity(new Intent(MainActivity.this, CertificateActivity.class));
                break;
            default:
                break;
        }
    }
}
