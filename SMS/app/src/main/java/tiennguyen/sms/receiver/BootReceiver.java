package tiennguyen.sms.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tiennguyen.sms.model.SmsPojo;
import tiennguyen.sms.utils.AppConstants;

/**
 * @author NguyenTien
 */
public class BootReceiver extends BroadcastReceiver {

    List<SmsPojo> smsPojos;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("NVTien", "BootReceiver onReceive()");
        smsPojos = new Select()
                .all()
                .from(SmsPojo.class)
                .where("status=?",0)
                .execute();
        if(smsPojos == null){
            smsPojos = new ArrayList<>();
        }

        Intent alarmIntent = new Intent(context, SmsAlarmReceiver.class);
        List<PendingIntent> pendingIntents = new ArrayList<>();
        for(SmsPojo smsPojo : smsPojos){
            long id = smsPojo.getId();
            alarmIntent.putExtra(AppConstants.KEY_CURRENT_SMS, id);
            int requestCode = (int) id;
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestCode, alarmIntent, PendingIntent.FLAG_ONE_SHOT);
            pendingIntents.add(pendingIntent);

            String[] fulldate = smsPojo.date.split(" ");
            String[] date;
            if (fulldate.length >= 3) {
                date = fulldate[2].split("-"); //date format is dd-MM-yyyy
            } else {
                date = fulldate[1].split("-"); //date format is dd-MM-yyyy
            }
            int year = Integer.parseInt(date[2]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[0]);
            String[] time = smsPojo.time.split(":"); //date format is HH:mm
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);

            //set data for calendar
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.MONTH, month - 1); // because Januari is index 0
            calendar.set(Calendar.DAY_OF_MONTH, day);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);

            AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

        }
    }
}
