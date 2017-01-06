package tiennguyen.sms.fragment;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
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
public class PendingSmsFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private SmsAdapter adapter;
    private ImageButton btnNew;
    private List<SmsPojo> smsPojos;
    private BroadcastReceiver mRefreshReceiver;
    private TextView tvNoMessage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    private void loadSmsPendingFromDatabase() {
        smsPojos = new Select()
                .all()
                .from(SmsPojo.class)
                .where("status=?", 0)
                .execute();
        if (smsPojos == null) {
            smsPojos = new ArrayList<>();

        }

        Collections.sort(smsPojos, new SmsPojo());  //sort by date&time
        adapter.setSmsPojos(smsPojos);
        adapter.notifyDataSetChanged();

        if (smsPojos != null && smsPojos.size() == 0) {
            tvNoMessage.setVisibility(View.VISIBLE);
        } else {
            tvNoMessage.setVisibility(View.GONE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_pending_sms, container, false);
        tvNoMessage = (TextView) view.findViewById(R.id.tvNoMessage);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        LinearLayoutManager llm = new LinearLayoutManager(mActivity);
        recyclerView.setLayoutManager(llm);
        adapter = new SmsAdapter();
        recyclerView.setAdapter(adapter);

        btnNew = (ImageButton) view.findViewById(R.id.btn_new);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if case new sms, send id = -1L
                PreferenceUtils.saveToPreferences(mActivity, PreferenceUtils.KEY_SMS_SELECTED_ID, -1L);
                ((MainActivity) getActivity()).findViewById(R.id.tabs).setVisibility(View.GONE);
                startFragment(R.id.root_frame, new ComposeSmsFragment());

            }
        });
        ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                SmsPojo smsPojo = smsPojos.get(position);
                long id = smsPojo.getId();
                PreferenceUtils.saveToPreferences(mActivity, PreferenceUtils.KEY_SMS_SELECTED_ID, id);
                startFragment(R.id.root_frame, new DetailSmsFragment());

            }
        });



        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        MainActivity.canExit = true;
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ((MainActivity) getActivity()).findViewById(R.id.tabs).setVisibility(View.VISIBLE);
        loadSmsPendingFromDatabase();

        mRefreshReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                loadSmsPendingFromDatabase();
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
