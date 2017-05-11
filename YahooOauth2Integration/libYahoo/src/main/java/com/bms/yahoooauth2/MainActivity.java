package com.bms.yahoooauth2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import component.DaggerActivityComponent;
import module.ActivityLevelModule;

public class MainActivity extends AppCompatActivity implements GetData {

    @Inject
    Activity activity;

    @Inject
    MainPresenter presenter;

    Button btn_click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intializeDependency();
        if (presenter != null) {
            presenter.intializeView(this);
            presenter.setGetData(this);
        }
        btn_click = (Button) findViewById(R.id.btn_click);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main_to_start = new Intent(MainActivity.this, StartingActivity.class);
                main_to_start.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(main_to_start, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                String code = data.getStringExtra(Constants.CODE);
                System.out.println("code" + code);
                Map<String, Object> params = new HashMap<>();
                params.put(Constants.CLIENT_ID_KEY, Constants.CLIENT_ID);
                params.put(Constants.CLIENT_SECRET_KEY, Constants.CLIENT_SECRET);
                params.put(Constants.REDIRECT_URI_KEY, Constants.REDIRECT_URI);
                params.put(Constants.CODE, code);
                params.put(Constants.GRANT_TYPE, Constants.GRANT_TYPE_VALUE);
                System.out.println("params" + params);
                System.out.println("basecode" + Utils.getBase(Constants.CLIENT_ID, Constants.CLIENT_SECRET));
                System.out.println("contenttype" + Constants.CONTENT_TYPE_VALUE);
                presenter.getResults(params, Constants.OAUTH_TOKEN_URL, Utils.getBase(Constants.CLIENT_ID, Constants.CLIENT_SECRET), Constants.CONTENT_TYPE_VALUE);
            }
        }
    }

    private void intializeDependency() {
        DaggerActivityComponent.builder().activityLevelModule(new ActivityLevelModule(this)).build().inject(this);
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.Destroy();
        }
        super.onDestroy();
    }


    @Override
    public void getData(Profile profile) {
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(new Gson().toJson(profile.getProfile()));
    }
}
