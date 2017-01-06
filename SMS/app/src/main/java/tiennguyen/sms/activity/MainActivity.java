package tiennguyen.sms.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import tiennguyen.sms.R;
import tiennguyen.sms.adapter.CustomFragmentPagerAdapter;
import tiennguyen.sms.fragment.RootListSmsFragment;
import tiennguyen.sms.fragment.RootSentSmsFragment;
import tiennguyen.sms.utils.AppConstants;


/**
 * @author NguyenTien
 */
public class MainActivity extends BaseActivity {

    public Toolbar toolbar;
    private TabLayout tabLayout;
    public ViewPager viewPager;
    public static int tabPosition;
    public static boolean canExit = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupDefaultActionBar();
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void setupDefaultActionBar() {
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_home));

        //getSupportActionBar().setTitle("S
        // MS Later");
    }

    public void setupBackButtonActionBar() {
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back));

    }

    private void setupViewPager(ViewPager viewPager) {
        CustomFragmentPagerAdapter adapter = new CustomFragmentPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new RootListSmsFragment(), AppConstants.SMS_PENDING);
        adapter.addFragment(new RootSentSmsFragment(), AppConstants.SMS_SENT);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(tabPosition);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if(canExit==false){
            super.onBackPressed();
        }else{
            showDialogConfirmExit();
        }

    }

}
