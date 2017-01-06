package tiennguyen.sms.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import tiennguyen.sms.model.SmsPojo;
import tiennguyen.sms.service.SmsService;
import tiennguyen.sms.utils.AppConstants;

/**
 * @author NguyenTien
 */
public class SmsAlarmReceiver extends BroadcastReceiver {
    private SmsPojo currentSms;
    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        long idSms = intent.getLongExtra(AppConstants.KEY_CURRENT_SMS, 0L);
        Log.e("NVTien", "receive sms id: " + idSms);

        Intent myIntent = new Intent(context, SmsService.class);
        myIntent.putExtra(AppConstants.KEY_CURRENT_SMS, idSms);
        context.startService(myIntent);

    }

}
