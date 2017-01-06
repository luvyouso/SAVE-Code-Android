package tiennguyen.sms.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tiennguyen.sms.R;
import tiennguyen.sms.activity.MainActivity;
import tiennguyen.sms.adapter.SmsAdapter;
import tiennguyen.sms.model.SmsPojo;
import tiennguyen.sms.utils.AppConstants;
import tiennguyen.sms.utils.ItemClickSupport;
import tiennguyen.sms.utils.PreferenceUtils;

/**
 * @author NguyenTien
 */
public class SentSmsFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private SmsAdapter adapter;
    private List<SmsPojo> smsPojos;
    private BroadcastReceiver mRefreshReceiver;
    private TextView tvNoMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void loadSentSmsFromDatabase() {
        smsPojos = new Select()
                .all()
                .from(SmsPojo.class)
                .where("status!=?", 0)
                .execute();
        if (smsPojos == null) {
            smsPojos = new ArrayList<>();
        }

        Collections.sort(smsPojos, new SmsPojo()); //sort by date&time
        adapter.setSmsPojos(smsPojos);
        adapter.notifyDataSetChanged();
        if (smsPojos == null || smsPojos.size() == 0) {
            tvNoMessage.setVisibility(View.VISIBLE);
        } else {
            tvNoMessage.setVisibility(View.GONE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sent_sms, container, false);
        tvNoMessage = (TextView) view.findViewById(R.id.tvNoMessage);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        LinearLayoutManager llm = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(llm);
        adapter = new SmsAdapter();
        adapter.setSmsPojos(smsPojos);
        recyclerView.setAdapter(adapter);

        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                SmsPojo smsPojo = smsPojos.get(position);
                long id = smsPojo.getId();
                Log.e("NVTien", "sms selected id: " + id);
                PreferenceUtils.saveToPreferences(mActivity, PreferenceUtils.KEY_SMS_SELECTED_ID, id);
                startFragment(R.id.root_frame2, new DetailSmsFragment());

            }
        });
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();
        MainActivity.canExit = true;
        ((MainActivity) getActivity()).findViewById(R.id.tabs).setVisibility(View.VISIBLE);
        loadSentSmsFromDatabase();

        mRefreshReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                Log.e("NVTien", "Receive broadcast from Service, SMS sent ");
                //update data for adapter
                loadSentSmsFromDatabase();

            }
        };

        IntentFilter mIntentFilter = new IntentFilter(AppConstants.ACTION_SMS_SENT);

        mActivity.registerReceiver(mRefreshReceiver, mIntentFilter);
        ((MainActivity) getActivity()).viewPager.setOnTouchListener(null);//enable swipe
        ((MainActivity) getActivity()).setupDefaultActionBar();
    }

    @Override
    public void onPause() {
        super.onPause();
        mActivity.unregisterReceiver(mRefreshReceiver);
    }
}
