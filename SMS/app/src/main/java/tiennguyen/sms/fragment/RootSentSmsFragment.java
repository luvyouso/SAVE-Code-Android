package tiennguyen.sms.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tiennguyen.sms.R;


/**
 * @author NguyenTien
 */
public class RootSentSmsFragment extends Fragment {

    private static final String TAG = "RootListSmsFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* Inflate the layout for this fragment */
        View view = inflater.inflate(R.layout.fragment_root_sent_sms, container, false);
		

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction();
		/*
		 * When this container fragment is created, we fill it with our first
		 * "real" fragment
		 */
        transaction.replace(R.id.root_frame2, new SentSmsFragment());

        transaction.commit();

        return view;
    }

}
