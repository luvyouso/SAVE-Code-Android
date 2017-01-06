package tiennguyen.sms.application;

import android.app.Application;
import android.content.Context;

import com.activeandroid.ActiveAndroid;

import tiennguyen.sms.utils.SoundPoolManager;


/**
 * @author NguyenTien
 */
public class AppApplication extends Application {


    private static AppApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ActiveAndroid.initialize(this);
        SoundPoolManager.getInstance(this).loadSound();


    }


    public static AppApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return instance.getApplicationContext();
    }

}
