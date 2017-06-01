package jp.daiwahouse.droomapp.test.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import jp.daiwahouse.droomapp.test.R;
import jp.daiwahouse.droomapp.test.databinding.ActivityCertificateBinding;

public class CertificateActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityCertificateBinding mCertificateBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCertificateBinding = DataBindingUtil.setContentView(this, R.layout.activity_certificate);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera:

                break;
            case R.id.back:
                break;

            default:
                break;

        }
    }
}
