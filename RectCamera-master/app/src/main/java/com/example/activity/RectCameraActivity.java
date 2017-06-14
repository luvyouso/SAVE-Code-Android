package com.example.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.FileTools;
import com.example.camera.CameraHelper;
import com.example.camera.OnCaptureCallback;
import com.example.view.MaskSurfaceView;

import java.io.File;
import java.util.ArrayList;

public class RectCameraActivity extends Activity implements OnCaptureCallback{

	private MaskSurfaceView surfaceview;
	private ImageView imageView;
//	拍照
	private Button btn_capture;
//	重拍
	private Button btn_recapture;
//	取消
	private Button btn_cancel;
//	确认
	private Button btn_ok;

	//调焦
	private SeekBar sb_zoom;
	
//	拍照后得到的保存的文件路径
	private String filepath;
	public int PERMISSION_REQUEST_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		activityRequestPermissions(PERMISSION_REQUEST_CODE);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.activity_rect_camera);
		
		this.surfaceview = (MaskSurfaceView) findViewById(R.id.surface_view);
		this.imageView = (ImageView) findViewById(R.id.image_view);
		btn_capture = (Button) findViewById(R.id.btn_capture);
		btn_recapture = (Button) findViewById(R.id.btn_recapture);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		btn_cancel = (Button) findViewById(R.id.btn_cancel);
		sb_zoom = (SeekBar) findViewById(R.id.sb_zoom);
		
//		设置矩形区域大小
		this.surfaceview.setMaskSize(400, 400);
		
//		拍照
		btn_capture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				btn_capture.setEnabled(false);
				btn_ok.setEnabled(true);
				btn_recapture.setEnabled(true);
				CameraHelper.getInstance().setFlashlight(CameraHelper.Flashlight.OFF).tackPicture(RectCameraActivity.this);
			}
		});
		
//		重拍
		btn_recapture.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				btn_capture.setEnabled(true);
				btn_ok.setEnabled(false);
				btn_recapture.setEnabled(false);
				imageView.setVisibility(View.GONE);
				surfaceview.setVisibility(View.VISIBLE);
				deleteFile();
				CameraHelper.getInstance().setFlashlight(CameraHelper.Flashlight.OFF).startPreview();
			}
		});
		
//		确认
		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				FileTools.savePhotoToSDCard(Environment.getExternalStorageDirectory()+"/", "test.jpg", BitmapFactory
						.decodeFile(filepath));
				Toast.makeText(RectCameraActivity.this, "pic save success", Toast.LENGTH_SHORT).show();
			}
		});
		
//		取消
		btn_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				deleteFile();
				RectCameraActivity.this.finish();
			}
		});

		//调焦
		sb_zoom.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				CameraHelper.getInstance().setZoom(progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});
	}
	
	/**
	 * 删除图片文件呢
	 */
	private void deleteFile(){
		if(this.filepath==null || this.filepath.equals("")){
			return;
		}
		File f = new File(this.filepath);
		if(f.exists()){
			f.delete();
		}
	}
	public static ArrayList<String> getSettingPermissions(Context context) {
		ArrayList<String> list = new ArrayList<String>();
		PackageInfo packageInfo = null;
		try {
			packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_PERMISSIONS);
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		if (packageInfo == null || packageInfo.requestedPermissions == null) return list;

		for (String permission : packageInfo.requestedPermissions) {
			list.add(permission);
		}
		return list;
	}

	public static boolean hasSelfPermission(Context context, String permission) {
		if (Build.VERSION.SDK_INT < 23) return true;
		return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
	}
	private boolean activityRequestPermissions(int requestCode) {
		if (Build.VERSION.SDK_INT >= 23) {
			ArrayList<String> permissions = getSettingPermissions(this);
			boolean isRequestPermission = false;
			for (String permission : permissions) {
				if (!hasSelfPermission(this, permission)) {
					isRequestPermission = true;
					break;
				}
			}
			if (isRequestPermission) {
				requestPermissions(permissions.toArray(new String[0]), requestCode);
				return true;
			}
		}
		return false;
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		if (requestCode == PERMISSION_REQUEST_CODE) {

			// 許可されたパーミッションがあるかを確認する
			boolean isSomethingGranted = false;
			for (int grantResult : grantResults) {
				if (grantResult == PackageManager.PERMISSION_GRANTED) {
					isSomethingGranted = true;
					break;
				}
			}

			if (isSomethingGranted) {
				// 設定を変更してもらえた場合、処理を継続する
			} else {
				// 設定を変更してもらえなかった場合、終了
				Toast.makeText(this, "権限取得エラー", Toast.LENGTH_LONG).show();
				finish();
			}
		}
	}

	@Override
	public void onCapture(boolean success, String filepath) {
		this.filepath = filepath;
		String message = "拍照成功";
		if(!success){
			message = "拍照失败";
			CameraHelper.getInstance().startPreview();
			this.imageView.setVisibility(View.GONE);
			this.surfaceview.setVisibility(View.VISIBLE);
		}else{
			this.imageView.setVisibility(View.VISIBLE);
			this.surfaceview.setVisibility(View.GONE);
			this.imageView.setImageBitmap(BitmapFactory.decodeFile(filepath));
		}
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
}
