package com.example.nguyentu.demoshowpdfonline;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

public class MainActivity extends AppCompatActivity implements DownloadFile.Listener, ViewPager.OnPageChangeListener {

    private TextView mTextViewShow;
    private FrameLayout mViewContent;
    private RemotePDFViewPager remotePDFViewPager;
    private final String url = "http://www.adobe.com/content/dam/Adobe/en/devnet/acrobat/pdfs/adobe_supplement_iso32000.pdf";
    private PDFPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewContent = (FrameLayout) findViewById(R.id.viewRoot);
        mTextViewShow = (TextView) findViewById(R.id.textView);
        remotePDFViewPager = new RemotePDFViewPager(this, url, this);

    }

    public void updateLayout() {
        mViewContent.removeAllViewsInLayout();
        mViewContent.addView(remotePDFViewPager,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onSuccess(String url, String destinationPath) {
        mAdapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(mAdapter);
        remotePDFViewPager.addOnPageChangeListener(this);
        updateLayout();
    }

    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.close();
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mTextViewShow.setText(position + "");
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
