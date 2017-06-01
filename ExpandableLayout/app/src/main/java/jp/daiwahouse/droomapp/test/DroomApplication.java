package jp.daiwahouse.droomapp.test;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import jp.co.geniee.sdk.messaging.GenieeMessaging;

/**
 * Created by nguyentu on 5/24/17.
 */

public class DroomApplication extends Application {

    private final String TAG = "DroomApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        initialize();
    }

    public void initialize() {
        // Push SDK を初期化します。
        GenieeMessaging.Configuration configuration = createConfiguration().setSandboxModeEnabled(true);
        GenieeMessaging.initialize(this, "50", configuration); // input your app ID
    }

    private GenieeMessaging.Configuration createConfiguration() {

        GenieeMessaging.Configuration configuration = new GenieeMessaging.Configuration();
        GenieeMessaging.Configuration.NotificationStyle notificationStyle = new GenieeMessaging.Configuration.NotificationStyle();

        configuration.setCallback(new GenieeMessaging.Callback() {
            @Override
            public void onRegisterUser() {
                String message = "プッシュ通知サーバーへのユーザー登録処理が完了しました";
                Toast.makeText(DroomApplication.this, message, Toast.LENGTH_SHORT).show();
                Log.i(TAG, message);
            }

            @Override
            public void onRegisterPush() {
                String message = "プッシュ通知サーバーへの端末登録処理が完了しました";
                Toast.makeText(DroomApplication.this, message, Toast.LENGTH_SHORT).show();
                Log.i(TAG, message);
            }

            @Override
            public void onReceiveNotification(GenieeMessaging.NotificationEvent notificationEvent, boolean isBackground) {
                String message = notificationEvent.getRemoteNotification() ? "リモート通知を受信しました" : "ローカル通知を受信しました";
                Toast.makeText(DroomApplication.this, message, Toast.LENGTH_SHORT).show();
                Log.i(TAG, message);
            }

            @Override
            public void onLaunchFromNotification(GenieeMessaging.NotificationEvent notificationEvent) {
                String message = notificationEvent.getRemoteNotification() ? "リモート通知から起動しました" : "ローカルプッシュから起動しました";
                Toast.makeText(DroomApplication.this, message, Toast.LENGTH_SHORT).show();
                Log.i(TAG, message);
            }
        });

        return configuration;
    }


}
