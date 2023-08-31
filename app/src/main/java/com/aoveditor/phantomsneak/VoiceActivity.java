package com.aoveditor.phantomsneak;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.RootTools.*;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.ktx.*;
import com.stericson.RootShell.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;
import java.io.*;
import java.util.zip.*;
import android.provider.DocumentsContract;
import android.provider.DocumentsContract.Document;
import android.database.*;
import androidx.documentfile.provider.DocumentFile;
import java.net.URL;
import java.net.HttpURLConnection;
import java.lang.Process;

import android.app.Activity;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.RequestConfiguration;
import android.widget.LinearLayout.LayoutParams;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdSize;

import com.stericson.RootTools.*;
import android.os.Looper;

public class VoiceActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private boolean quit = false;
	private String skin_string = "";
	private String lobby_string = "";
	private String other_string = "";
	private String EU_DLC = "";
	private  Uri uriA;
	private  Uri muri;
	private  Uri uri2;
	private  DocumentFile mfile;
	private  DocumentFile mfile1;
	private String JP_DLC = "";
	private String EU_Ver = "";
	private String JP_Ver = "";
	private double cnt = 0;
	private boolean inerstitialLoaded = false;
	public InterstitialAd mInterstitialAd;
	private boolean haveSU = false;
	private ProgressDialog prog;
	
	private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> map2 = new ArrayList<>();
	private ArrayList<String> sound_list = new ArrayList<>();
	private ArrayList<String> DLC_list = new ArrayList<>();
	private ArrayList<String> JP = new ArrayList<>();
	private ArrayList<String> EN = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private ImageView imageview12;
	private LinearLayout linear9;
	private ImageView imageview13;
	private ScrollView vscroll1;
	private ImageView imageview15;
	private TextView textview1;
	private Button button1;
	private ImageView imageview14;
	private TextView textview2;
	private Button button2;
	private LinearLayout linear11;
	private LinearLayout linear10;
	private LinearLayout bannerAd;
	private ImageView imageview16;
	private TextView textview3;
	private TextView textview4;
	private TextView textview9;
	private TextView textview12;
	private ImageView imageview17;
	private ImageView imageview18;
	private TextView textview5;
	private TextView textview6;
	private TextView textview10;
	private TextView textview13;
	private ImageView imageview22;
	private ImageView imageview23;
	private TextView textview8;
	private TextView textview7;
	private TextView textview11;
	private ImageView imageview7;
	private ImageView imageview8;
	private ImageView imageview9;
	private ImageView imageview10;
	private ImageView imageview11;
	
	private Intent page = new Intent();
	private DatabaseReference string_skin = _firebase.getReference("string_skin");
	private ChildEventListener _string_skin_child_listener;
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	private AlertDialog.Builder dialog;
	private DatabaseReference DLC = _firebase.getReference("eudlc");
	private ChildEventListener _DLC_child_listener;
	private AlertDialog.Builder delete;
	private AlertDialog.Builder auto_or_not;
	private TimerTask t_internet;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.voice);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		linear1 = findViewById(R.id.linear1);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		imageview12 = findViewById(R.id.imageview12);
		linear9 = findViewById(R.id.linear9);
		imageview13 = findViewById(R.id.imageview13);
		vscroll1 = findViewById(R.id.vscroll1);
		imageview15 = findViewById(R.id.imageview15);
		textview1 = findViewById(R.id.textview1);
		button1 = findViewById(R.id.button1);
		imageview14 = findViewById(R.id.imageview14);
		textview2 = findViewById(R.id.textview2);
		button2 = findViewById(R.id.button2);
		linear11 = findViewById(R.id.linear11);
		linear10 = findViewById(R.id.linear10);
		bannerAd = findViewById(R.id.bannerAd);
		imageview16 = findViewById(R.id.imageview16);
		textview3 = findViewById(R.id.textview3);
		textview4 = findViewById(R.id.textview4);
		textview9 = findViewById(R.id.textview9);
		textview12 = findViewById(R.id.textview12);
		imageview17 = findViewById(R.id.imageview17);
		imageview18 = findViewById(R.id.imageview18);
		textview5 = findViewById(R.id.textview5);
		textview6 = findViewById(R.id.textview6);
		textview10 = findViewById(R.id.textview10);
		textview13 = findViewById(R.id.textview13);
		imageview22 = findViewById(R.id.imageview22);
		imageview23 = findViewById(R.id.imageview23);
		textview8 = findViewById(R.id.textview8);
		textview7 = findViewById(R.id.textview7);
		textview11 = findViewById(R.id.textview11);
		imageview7 = findViewById(R.id.imageview7);
		imageview8 = findViewById(R.id.imageview8);
		imageview9 = findViewById(R.id.imageview9);
		imageview10 = findViewById(R.id.imageview10);
		imageview11 = findViewById(R.id.imageview11);
		net = new RequestNetwork(this);
		dialog = new AlertDialog.Builder(this);
		delete = new AlertDialog.Builder(this);
		auto_or_not = new AlertDialog.Builder(this);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice");
				FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice");
				textview9.setText("當前版本：".concat("窩不知道"));
				textview10.setText("當前版本：".concat("窩不知道"));
				showMessage("刪除成功");
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				delete.setTitle("提醒");
				delete.setIcon(R.drawable.downloadlogo);
				delete.setMessage("按下確認後，將還原語音修改\n(需要進入遊戲重新下載語音資源)");
				delete.setCancelable(false);
				delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
							try {
								uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC");
								try {
									try{
										        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
										     
										        } catch (FileNotFoundException e) {
										         
										    }             
								} catch (Exception e) {
									 
								}
								showMessage("還原成功");
							} catch (Exception e) {
								showMessage("自動模式還原失敗");
								showMessage("請使用MT手動刪除");
							}
						} else {
							showMessage("正在還原，請耐心等待至完成");
							FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC");
							showMessage("完成");
						}
					}
				});
				delete.setNeutralButton("取消", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				delete.create().show();
			}
		});
		
		imageview16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/JP/".concat(JP_Ver))) {
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
						auto_or_not.setTitle("提示");
						auto_or_not.setIcon(R.drawable.downloadlogo);
						auto_or_not.setMessage("使用自動模式或手動模式？\n自動模式速度較慢，但無須使用MT管理器手動啟用\n手動模式即使用MT管理器按照教學影片進行啟用");
						if (haveSU) {
							auto_or_not.setNegativeButton("root自動模式(快)", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									ProgressBar_Show("請稍等...");
									new BackgroundTaskClass(VoiceActivity.this){
										@Override
										public void doInBackground() {
											Looper.prepare();
											FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android");
											_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/JP/"+(JP_Ver), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android/");
										}
										@Override
										public void onPostExecute(){
											ProgressBar_Dismiss();
											
											ProgressBar_Show("啟用中...");
											new BackgroundTaskClass(VoiceActivity.this){
												@Override
												public void doInBackground() {
													Looper.prepare();
													//root複製
													CopyWithhaveSU("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android", true);
												}
												@Override
												public void onPostExecute(){
													ProgressBar_Dismiss();
													showMessage("完成");
													FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android");
												}
											}.execute();
											//結束
										}
									}.execute();
									//結束
								}
							});
						}
						auto_or_not.setPositiveButton("手動模式", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								try {
									_openapp();
									showMessage("請參考YT影片-白魔法1.4.0 影片\n手動進行啟用");
									FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/MT_2.13.0.apk");
								} catch (Exception e) {
									showMessage("play商店政策因素\n暫時移除自行安裝功能");
									showMessage("請自行下載MT管理器後安裝");
									Intent playstore = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mt2.cn/download/"));
														startActivity(playstore);
									/*
if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/MT_2.13.0.apk")) {
_install_package();
}
else {
DownloadAndInstallMT("https://cdn.discordapp.com/attachments/842221289464004608/1074236961570689034/MT_2.13.0.apk", "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/");
}
*/
								}
							}
						});
						auto_or_not.setNeutralButton("自動模式", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								showMessage("啟用過程請勿跳離畫面");
								FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC");
								_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/JP/"+(JP_Ver), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/");
								showMessage("1 / 3 步驟啟用完成");
								FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/Chinese(Taiwan)/", sound_list);
								count=sound_list.size();
								final ProgressDialog prog = new ProgressDialog(VoiceActivity.this);
								prog.setIcon(R.drawable.downloadlogo);
								prog.setMax(100);
								prog.setIndeterminate(true);
								prog.setCancelable(false);
								prog.setCanceledOnTouchOutside(false);
								prog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
								prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
								new Thread(new Runnable() {
									@Override
									public void run() {
										for(int _repeat96 = 0; _repeat96 < (int)(sound_list.size()); _repeat96++) {
											try {
												uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2F"+Uri.parse(sound_list.get((count - 1))).getLastPathSegment());
												try{
													DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
												} catch (FileNotFoundException e) {	         
												}
											} catch (Exception e) {
											}
											uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2F");
											_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/Chinese(Taiwan)/"+Uri.parse(sound_list.get(count - 1)).getLastPathSegment());
											count--;
											runOnUiThread(new Runnable() {
												@Override
												public void run() {
													prog.setTitle("2 / 3 步驟啟用中...");
													prog.setMessage("\n剩餘檔案數量："+count);
													prog.show();
													if (count == 0) {
														prog.dismiss();
														sound_list.clear();
														FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/", sound_list);
														count=sound_list.size();
														final ProgressDialog prog = new ProgressDialog(VoiceActivity.this);
														prog.setIcon(R.drawable.downloadlogo);
														prog.setMax(100);
														prog.setIndeterminate(true);
														prog.setCancelable(false);
														prog.setCanceledOnTouchOutside(false);
														prog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
														prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
														new Thread(new Runnable() {
															@Override
															public void run() {
																for(int _repeat96 = 0; _repeat96 < (int)(sound_list.size()); _repeat96++) {
																	if (!Uri.parse(sound_list.get((count - 1))).getLastPathSegment().equals("Chinese(Taiwan)")){
																		try {
																			uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F"+Uri.parse(sound_list.get((count - 1))).getLastPathSegment());
																			try{
																				DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
																			} catch (FileNotFoundException e) {	         
																			}
																		} catch (Exception e) {
																		}
																		uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
																		_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/"+Uri.parse(sound_list.get(count - 1)).getLastPathSegment());
																	}
																	count--;
																	runOnUiThread(new Runnable() {
																		@Override
																		public void run() {
																			prog.setTitle("3 / 3 步驟啟用中...");
																			prog.setMessage("\n剩餘檔案數量："+count);
																			prog.show();
																			if (count == 0) {
																				prog.dismiss();
																				sound_list.clear();
																				FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC");
																				showMessage("完成");
																			}
																		} });
																}}}).start();
													}
												} });
										}}}).start();
							}
						});
						auto_or_not.create().show();
					} else {
						_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/JP/".concat(JP_Ver), "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/");
						showMessage("完成");
					}
				}
				else {
					if (inerstitialLoaded) {
						mInterstitialAd.show(VoiceActivity.this);
					}
					else {
						
					}
					FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice/JP"));
					FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice/JP"));
					_Download_Plugins("https://".concat(JP_DLC), "Android/data/com.aoveditor.phantomsneak/files/2-voice/", "JP/");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		imageview18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/EN/".concat(EU_Ver))) {
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
						auto_or_not.setTitle("提示");
						auto_or_not.setIcon(R.drawable.downloadlogo);
						auto_or_not.setMessage("使用自動模式或手動模式？\n自動模式速度較慢，但無須使用MT管理器手動啟用\n手動模式即使用MT管理器按照教學影片進行啟用");
						if (haveSU) {
							auto_or_not.setNegativeButton("root自動模式(快)", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									ProgressBar_Show("請稍等...");
									new BackgroundTaskClass(VoiceActivity.this){
										@Override
										public void doInBackground() {
											Looper.prepare();
											FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android");
											_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/EN/"+(EU_Ver), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android/");
										}
										@Override
										public void onPostExecute(){
											ProgressBar_Dismiss();
											
											ProgressBar_Show("啟用中...");
											new BackgroundTaskClass(VoiceActivity.this){
												@Override
												public void doInBackground() {
													Looper.prepare();
													//root複製
													CopyWithhaveSU("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android", true);
												}
												@Override
												public void onPostExecute(){
													ProgressBar_Dismiss();
													showMessage("完成");
													FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android");
												}
											}.execute();
											//結束
										}
									}.execute();
									//結束
								}
							});
						}
						auto_or_not.setPositiveButton("手動模式", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								try {
									_openapp();
									showMessage("請參考YT影片-白魔法1.4.0 影片\n手動進行啟用");
									FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/MT_2.13.0.apk");
								} catch (Exception e) {
									showMessage("play商店政策因素\n暫時移除自行安裝功能");
									showMessage("請自行下載MT管理器後安裝");
									Intent playstore = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mt2.cn/download/"));
														startActivity(playstore);
									/*
if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/MT_2.13.0.apk")) {
_install_package();
}
else {
DownloadAndInstallMT("https://cdn.discordapp.com/attachments/842221289464004608/1074236961570689034/MT_2.13.0.apk", "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/");
}
*/
								}
							}
						});
						auto_or_not.setNeutralButton("自動模式", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								showMessage("啟用過程請勿跳離畫面");
								FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC");
								_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/EN/"+(EU_Ver), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/");
								showMessage("1 / 3 步驟啟用完成");
								FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/Chinese(Taiwan)/", sound_list);
								count=sound_list.size();
								final ProgressDialog prog = new ProgressDialog(VoiceActivity.this);
								prog.setIcon(R.drawable.downloadlogo);
								prog.setMax(100);
								prog.setIndeterminate(true);
								prog.setCancelable(false);
								prog.setCanceledOnTouchOutside(false);
								prog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
								prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
								new Thread(new Runnable() {
									@Override
									public void run() {
										for(int _repeat96 = 0; _repeat96 < (int)(sound_list.size()); _repeat96++) {
											try {
												uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2F"+Uri.parse(sound_list.get((count - 1))).getLastPathSegment());
												try{
													DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
												} catch (FileNotFoundException e) {	         
												}
											} catch (Exception e) {
											}
											uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2F");
											_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/Chinese(Taiwan)/"+Uri.parse(sound_list.get(count - 1)).getLastPathSegment());
											count--;
											runOnUiThread(new Runnable() {
												@Override
												public void run() {
													prog.setTitle("2 / 3 步驟啟用中...");
													prog.setMessage("\n剩餘檔案數量："+count);
													prog.show();
													if (count == 0) {
														prog.dismiss();
														sound_list.clear();
														FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/", sound_list);
														count=sound_list.size();
														final ProgressDialog prog = new ProgressDialog(VoiceActivity.this);
														prog.setIcon(R.drawable.downloadlogo);
														prog.setMax(100);
														prog.setIndeterminate(true);
														prog.setCancelable(false);
														prog.setCanceledOnTouchOutside(false);
														prog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
														prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
														new Thread(new Runnable() {
															@Override
															public void run() {
																for(int _repeat96 = 0; _repeat96 < (int)(sound_list.size()); _repeat96++) {
																	if (!Uri.parse(sound_list.get((count - 1))).getLastPathSegment().equals("Chinese(Taiwan)")){
																		try {
																			uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F"+Uri.parse(sound_list.get((count - 1))).getLastPathSegment());
																			try{
																				DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
																			} catch (FileNotFoundException e) {	         
																			}
																		} catch (Exception e) {
																		}
																		uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
																		_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/"+Uri.parse(sound_list.get(count - 1)).getLastPathSegment());
																	}
																	count--;
																	runOnUiThread(new Runnable() {
																		@Override
																		public void run() {
																			prog.setTitle("3 / 3 步驟啟用中...");
																			prog.setMessage("\n剩餘檔案數量："+count);
																			prog.show();
																			if (count == 0) {
																				prog.dismiss();
																				sound_list.clear();
																				FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC");
																				showMessage("完成");
																			}
																		} });
																}}}).start();
													}
												} });
										}}}).start();
							}
						});
						auto_or_not.create().show();
					} else {
						_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/EN/".concat(EU_Ver), "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/");
						showMessage("完成");
					}
				}
				else {
					if (inerstitialLoaded) {
						mInterstitialAd.show(VoiceActivity.this);
					}
					else {
						
					}
					FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice/EN"));
					FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice/EN"));
					_Download_Plugins("https://".concat(EU_DLC), "Android/data/com.aoveditor.phantomsneak/files/2-voice/", "EN/");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		imageview23.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/KR_DLC.∠( ᐛ 」∠)＿")) {
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
						auto_or_not.setTitle("提示");
						auto_or_not.setIcon(R.drawable.downloadlogo);
						auto_or_not.setMessage("使用自動模式或手動模式？\n自動模式速度較慢，但無須使用MT管理器手動啟用\n手動模式即使用MT管理器按照教學影片進行啟用");
						if (haveSU) {
							auto_or_not.setNegativeButton("root自動模式(快)", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									ProgressBar_Show("請稍等...");
									new BackgroundTaskClass(VoiceActivity.this){
										@Override
										public void doInBackground() {
											Looper.prepare();
											FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android");
											_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/KR_DLC.∠( ᐛ 」∠)＿", "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android/");
										}
										@Override
										public void onPostExecute(){
											ProgressBar_Dismiss();
											
											ProgressBar_Show("啟用中...");
											new BackgroundTaskClass(VoiceActivity.this){
												@Override
												public void doInBackground() {
													Looper.prepare();
													//root複製
													CopyWithhaveSU("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android", true);
												}
												@Override
												public void onPostExecute(){
													ProgressBar_Dismiss();
													showMessage("完成");
													FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/Android");
												}
											}.execute();
											//結束
										}
									}.execute();
									//結束
								}
							});
						}
						auto_or_not.setPositiveButton("手動模式", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								try {
									_openapp();
									showMessage("請參考YT影片-白魔法1.4.0 影片\n手動進行啟用");
									FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/MT_2.13.0.apk");
								} catch (Exception e) {
									showMessage("play商店政策因素\n暫時移除自行安裝功能");
									showMessage("請自行下載MT管理器後安裝");
									Intent playstore = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mt2.cn/download/"));
														startActivity(playstore);
									/*
if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/MT_2.13.0.apk")) {
_install_package();
}
else {
DownloadAndInstallMT("https://cdn.discordapp.com/attachments/842221289464004608/1074236961570689034/MT_2.13.0.apk", "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/");
}
*/
								}
							}
						});
						auto_or_not.setNeutralButton("自動模式", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								showMessage("啟用過程請勿跳離畫面");
								FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC");
								_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/KR_DLC.∠( ᐛ 」∠)＿", "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/");
								showMessage("1 / 3 步驟啟用完成");
								FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/Chinese(Taiwan)/", sound_list);
								count=sound_list.size();
								final ProgressDialog prog = new ProgressDialog(VoiceActivity.this);
								prog.setIcon(R.drawable.downloadlogo);
								prog.setMax(100);
								prog.setIndeterminate(true);
								prog.setCancelable(false);
								prog.setCanceledOnTouchOutside(false);
								prog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
								prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
								new Thread(new Runnable() {
									@Override
									public void run() {
										for(int _repeat96 = 0; _repeat96 < (int)(sound_list.size()); _repeat96++) {
											try {
												uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2F"+Uri.parse(sound_list.get((count - 1))).getLastPathSegment());
												try{
													DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
												} catch (FileNotFoundException e) {	         
												}
											} catch (Exception e) {
											}
											uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2F");
											_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/Chinese(Taiwan)/"+Uri.parse(sound_list.get(count - 1)).getLastPathSegment());
											count--;
											runOnUiThread(new Runnable() {
												@Override
												public void run() {
													prog.setTitle("2 / 3 步驟啟用中...");
													prog.setMessage("\n剩餘檔案數量："+count);
													prog.show();
													if (count == 0) {
														prog.dismiss();
														sound_list.clear();
														FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/", sound_list);
														count=sound_list.size();
														final ProgressDialog prog = new ProgressDialog(VoiceActivity.this);
														prog.setIcon(R.drawable.downloadlogo);
														prog.setMax(100);
														prog.setIndeterminate(true);
														prog.setCancelable(false);
														prog.setCanceledOnTouchOutside(false);
														prog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
														prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
														new Thread(new Runnable() {
															@Override
															public void run() {
																for(int _repeat96 = 0; _repeat96 < (int)(sound_list.size()); _repeat96++) {
																	if (!Uri.parse(sound_list.get((count - 1))).getLastPathSegment().equals("Chinese(Taiwan)")){
																		try {
																			uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F"+Uri.parse(sound_list.get((count - 1))).getLastPathSegment());
																			try{
																				DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
																			} catch (FileNotFoundException e) {	         
																			}
																		} catch (Exception e) {
																		}
																		uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
																		_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC/"+Uri.parse(sound_list.get(count - 1)).getLastPathSegment());
																	}
																	count--;
																	runOnUiThread(new Runnable() {
																		@Override
																		public void run() {
																			prog.setTitle("3 / 3 步驟啟用中...");
																			prog.setMessage("\n剩餘檔案數量："+count);
																			prog.show();
																			if (count == 0) {
																				prog.dismiss();
																				sound_list.clear();
																				FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/DLC");
																				showMessage("完成");
																			}
																		} });
																}}}).start();
													}
												} });
										}}}).start();
							}
						});
						auto_or_not.create().show();
					} else {
						_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/KR_DLC.∠( ᐛ 」∠)＿", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/");
						showMessage("完成");
					}
				}
				else {
					if (inerstitialLoaded) {
						mInterstitialAd.show(VoiceActivity.this);
					}
					else {
						
					}
					FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice"));
					_Download_Plugins("https://www.dropbox.com/s/lgaf0isyyxrddnn/KR_DLC.%E2%88%A0%28%20%E1%90%9B%20%E3%80%8D%E2%88%A0%29%EF%BC%BF?dl=1", "Android/data/com.aoveditor.phantomsneak/files/2-voice", "/");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		imageview7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				page.setClass(getApplicationContext(), HomeActivity.class);
				startActivity(page);
				overridePendingTransition(0, 0);
			}
		});
		
		imageview8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (skin_string.equals("") || internet == 0) {
						showMessage("初始化中..");
				}else if (skin_string.contains("停用")) {
						showMessage("無法使用");
				}else if (skin_string.contains("更新")) {
						showMessage("更新中");
				} else {
						page.setClass(getApplicationContext(), SkinActivity.class);
						startActivity(page);
						overridePendingTransition(0, 0);
				}
			}
		});
		
		imageview10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (lobby_string.equals("") || internet == 0) {
						showMessage("初始化中..");
				}else if (lobby_string.contains("停用")) {
						showMessage("無法使用");
				}else if (lobby_string.contains("更新")) {
						showMessage("更新中");
				} else {
						page.setClass(getApplicationContext(), LobbyActivity.class);
						startActivity(page);
						overridePendingTransition(0, 0);
				}
			}
		});
		
		imageview11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (other_string.equals("") || internet == 0) {
						showMessage("初始化中..");
				}else if (other_string.contains("停用")) {
						showMessage("無法使用");
				}else if (other_string.contains("更新")) {
						showMessage("更新中");
				} else {
						page.setClass(getApplicationContext(), OtherActivity.class);
						startActivity(page);
						overridePendingTransition(0, 0);
				}
			}
		});
		
		_string_skin_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				string_skin.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						map1 = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								map1.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						skin_string = map1.get((int)0).get("1-skin").toString();
						lobby_string = map1.get((int)0).get("2-lobby").toString();
						other_string = map1.get((int)0).get("3-other").toString();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		string_skin.addChildEventListener(_string_skin_child_listener);
		
		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				internet =1;
				t_internet = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Internet();
							}
						});
					}
				};
				_timer.schedule(t_internet, (int)(7000));
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				internet = 0;
				dialog.setTitle("錯誤");
				dialog.setMessage("目前網路不可用，請檢查是否連接了可用的Wifi或行動網路");
				dialog.setCancelable(false);
				dialog.setPositiveButton("重試", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						_Internet();
					}
				});
				dialog.create().show();
			}
		};
		
		_DLC_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				DLC.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						map2 = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								map2.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						EU_DLC = map2.get((int)0).get("eu").toString();
						JP_DLC = map2.get((int)0).get("jp").toString();
						EU_Ver = map2.get((int)0).get("eu_ver").toString();
						JP_Ver = map2.get((int)0).get("jp_ver").toString();
						textview12.setText("最新版本：".concat(JP_Ver));
						textview13.setText("最新版本：".concat(EU_Ver));
						FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/JP/", JP);
						FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/EN/", EN);
						if (JP.size() == 0) {
							textview9.setText("當前版本：".concat("窩不知道"));
						}
						else {
							textview9.setText("當前版本：".concat(Uri.parse(JP.get((int)(0))).getLastPathSegment()));
						}
						if (EN.size() == 0) {
							textview10.setText("當前版本：".concat("窩不知道"));
						}
						else {
							textview10.setText("當前版本：".concat(Uri.parse(EN.get((int)(0))).getLastPathSegment()));
						}
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { //讓root的安卓11以上可用root模式啟用(もっと速く)
							new Thread() {
								public void run() {
									if (_RootAccess()) {
										haveSU = true;
									}
									else {
										haveSU = false;
									}
								}
							}.start();
						}
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		DLC.addChildEventListener(_DLC_child_listener);
	}
	
	private void initializeLogic() {
		if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/jpv.png")) {
			imageview16.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/jpv.png", 1024, 1024));
		}
		else {
			DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074555602057048064/jpv.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/", imageview16);
		}
		if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/env.png")) {
			imageview18.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/env.png", 1024, 1024));
		}
		else {
			DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074555601813766154/env.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/", imageview18);
		}
		if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/krv.png")) {
			imageview23.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/krv.png", 1024, 1024));
		}
		else {
			DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074555602342248498/krv.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice/", imageview23);
		}
		FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice/JP"));
		FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/2-voice/EN"));
		quit = false;
		_loadInerstitialAd();
		AdView banner2 = new AdView(VoiceActivity.this);
		banner2.setAdSize(AdSize.BANNER);
		banner2.setAdUnitId("ca-app-pub-3897977034034314/1209563365");
		AdRequest arbanner2 = new AdRequest.Builder().build();
		banner2.loadAd(arbanner2);
		LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		bannerAd.addView(banner2,p2);
		_Internet();
	}
	
	@Override
	public void onBackPressed() {
		if (quit == false) {//詢問退出程序
				showMessage("再按一次退出應用");
				new Timer(true).schedule(new TimerTask() {//啟動定時任務
						@Override
						public void run() {
								quit = false;//重置退出標示
						}
				    }, 2000);//2秒後執行run()方法
			    quit = true;
		} else {//確認退出應用
				super.onBackPressed();
				finishAffinity();
		}
	}
	
	public void _Internet() {
		net.startRequestNetwork(RequestNetworkController.GET, "https://1.1.1.1", "", _net_request_listener);
	}
	
	
	public void _Download(final String _url, final String _path) {
		try{
				DownloadManager.Request request = new DownloadManager.Request(Uri.parse( _url)); 
				request.setMimeType(this.getContentResolver().getType(Uri.parse(_url))); 
				String cookies = CookieManager.getInstance().getCookie(_url); 
				request.addRequestHeader("cookie", cookies); 
				//request.addRequestHeader("User-Agent", tab.getSettings().getUserAgentString());
				request.setDescription("正在下載插件"); 
				request.setTitle(URLUtil.guessFileName(_url,"",""));
				request.allowScanningByMediaScanner(); 
				request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); 
			    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
						Uri destinationUri = Uri.fromFile(new File("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/"+URLUtil.guessFileName(_url,"","")));
						request.setDestinationUri (destinationUri);
				}else{
						request.setDestinationInExternalPublicDir( _path.equals("")?Environment.DIRECTORY_DOWNLOADS:_path, URLUtil.guessFileName(_url,"","")); 
				}
				DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE); dm.enqueue(request);
				showMessage("正在下載插件...");
		}catch(Exception e){
				showMessage(e.toString());
		}
	}
	
	
	public void _unzip(final String _fileZip, final String _destDir) {
		try{
				java.io.File outdir = new java.io.File(_destDir);
				java.util.zip.ZipInputStream zin = new java.util.zip.ZipInputStream(new java.io.FileInputStream(_fileZip));
				java.util.zip.ZipEntry entry;
				String name, dir;
				while ((entry = zin.getNextEntry()) != null){
						
						name = entry.getName();
						if(entry.isDirectory())
						{
								mkdirs(outdir, name);
								continue;
						}
						
						/* this part is necessary because file entry can come before
		* directory entry where is file located
		* i.e.:
		* /foo/foo.txt
		* /foo/
		*/
						
						dir = dirpart(name);
						if(dir != null)
						mkdirs(outdir, dir);
						
						extractFile(zin, outdir, name);
				}
				zin.close();
		}
		catch (java.io.IOException e){
				e.printStackTrace();
		}
		
	}
	private static void extractFile(java.util.zip.ZipInputStream in, java.io.File outdir, String name) throws java.io.IOException{
			byte[] buffer = new byte[4096];
			java.io.BufferedOutputStream out = new java.io.BufferedOutputStream(new java.io.FileOutputStream(new java.io.File(outdir, name)));
			int count = -1;
			while ((count = in.read(buffer)) != -1)
			out.write(buffer, 0, count);
			out.close();
	}
	
	private static void mkdirs(java.io.File outdir, String path){
			java.io.File d = new java.io.File(outdir, path);
			if(!d.exists())
			d.mkdirs();
	}
	
	private static String dirpart(String name){
			int s = name.lastIndexOf(java.io.File.separatorChar);
			return s == -1 ? null : name.substring(0, s);
			
	}
	
	
	public void _openapp() {
		Intent launchIntent = getPackageManager().getLaunchIntentForPackage("bin.mt.plus");
		startActivity(launchIntent);
	}
	
	
	public void _install_package() {
		try {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
						Uri uri = androidx.core.content.FileProvider.getUriForFile(getApplicationContext(), VoiceActivity.this.getPackageName() + ".provider", new java.io.File("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/MT_2.13.0.apk"));
				        Intent intent = new Intent(Intent.ACTION_VIEW);
				        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
				        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				        intent.setDataAndType(uri, "application/vnd.android.package-archive");
				        startActivity(intent);
				    } else {
				        Intent intent = new Intent(Intent.ACTION_VIEW);
				        intent.setDataAndType(Uri.fromFile( new java.io.File("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/MT_2.13.0.apk")), "application/vnd.android.package-archive");
				        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				        startActivity(intent);
				    }
		} catch (Exception rr) {
			    showMessage (rr.toString());
		}
	}
	
	
	public void _Extra() {
	}
	// url = file path or whatever suitable URL you want.
	public static String getMimeType(String url) {
		    String type = null;
		    String extension = MimeTypeMap.getFileExtensionFromUrl(url);
		    if (extension != null) {
			        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
			    }
		    return type;
	}
	private int count;
	private int internet;
	public boolean copyFilePath2Uri(Context context, File inputfile, Uri targetUri){
			InputStream fis = null;
			OutputStream fos = null;
			
			try {
					
					ContentResolver content = context.getContentResolver();
					fis = new FileInputStream(inputfile);
					fos = content.openOutputStream(targetUri);
					
					byte[] buff = new byte[1024];
					int length = 0;
					
					while ((length = fis.read(buff)) > 0) {
							fos.write(buff, 0, length);
					}
			} catch (IOException e) {
					return false;
			} finally {
					if (fis != null) {
							try {
									fis.close();
							} catch (IOException e) {
									return false;
							}
					}
					if (fos != null) {
							try {
									fos.close();
									
							} catch (IOException e) {
									return false;
							}
					}
			}
			return true;
	}
	
	public void DLC(String Url, String Path, ImageView view){
		Runnable updatethread = new Runnable() {
			public void run() {
				try {
					android.net.ConnectivityManager connMgr = (android.net.ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
					android.net.NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
					Looper.prepare();
					if (networkInfo != null && networkInfo.isConnected()) {
						URL url = new URL(Url);
						HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
						long completeFileSize = httpConnection.getContentLength();
						String filename;
						filename = URLUtil.guessFileName(Url, null, null);
						java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
						java.io.FileOutputStream fos = new java.io.FileOutputStream(Path + filename);
						java.io.BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
						byte[] data = new byte[1024];
						long downloadedFileSize = 0;
						int x = 0;
						while ((x = in.read(data, 0, 1024)) >= 0) {
							downloadedFileSize += x;
							// calculate progress
							final int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100000d);
							// update progress bar
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									//prog
								}
							});
							bout.write(data, 0, x);
						}
						bout.close();
						in.close();
						try{
							File IMG = new File(Path + filename);
							Bitmap bitmap = BitmapFactory.decodeFile(IMG.getAbsolutePath());
							view.setImageBitmap(bitmap);
						} catch (Exception e) {
						}
					} else {
						showMessage("無網際網路連線");
					}
					Looper.loop();
				} catch (FileNotFoundException e) {
					showMessage(e.getMessage());
				} catch (IOException e) {
					showMessage(e.getMessage());
				}
			}
		};
		new Thread(updatethread).start();
	}
	
	
	public void DownloadAndInstallMT(String Url, String Path){
		final ProgressDialog prog = new ProgressDialog(this);
		prog.setIcon(R.drawable.downloadlogo);
		prog.setMax(100);
		prog.setIndeterminate(true);
		prog.setCancelable(false);
		prog.setCanceledOnTouchOutside(false);
		prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
		Runnable updatethread = new Runnable() {
			public void run() {
				try {
					android.net.ConnectivityManager connMgr = (android.net.ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
					android.net.NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
					Looper.prepare();
					if (networkInfo != null && networkInfo.isConnected()) {
						URL url = new URL(Url);
						HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
						long completeFileSize = httpConnection.getContentLength();
						String filename;
						filename = URLUtil.guessFileName(Url, null, null);
						java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
						java.io.FileOutputStream fos = new java.io.FileOutputStream(Path + filename);
						java.io.BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
						byte[] data = new byte[1024];
						long downloadedFileSize = 0;
						int x = 0;
						while ((x = in.read(data, 0, 1024)) >= 0) {
							downloadedFileSize += x;
							// calculate progress
							final int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100000d);
							// update progress bar
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									prog.setTitle("正在下載MT管理器");
									prog.setMessage("正在為您下載 MT管理器\n下載進度：" + (currentProgress/1000) + "%");
									prog.show();
								}
							});
							bout.write(data, 0, x);
						}
						bout.close();
						in.close();
						prog.dismiss();
						_install_package();
					} else {
						showMessage("無網際網路連線");
					}
					Looper.loop();
				} catch (FileNotFoundException e) {
					showMessage(e.getMessage());
				} catch (IOException e) {
					showMessage(e.getMessage());
				}
			}
		};
		new Thread(updatethread).start();
	}
	
	public void CopyWithhaveSU(String original, String target, boolean exist_in_target_dest) {
		String comando;
		try {
			if (exist_in_target_dest){ //複製覆蓋
				String a = "cp -r " + original + " " + target;
				int len = a.lastIndexOf("/");
				comando = a.substring(0, len+1);
			} else {
				comando = "cp -r " + original + " " + target;
			}
			Process suProcess = Runtime.getRuntime().exec("su");
			DataOutputStream os = new DataOutputStream(suProcess.getOutputStream());
			os.writeBytes(comando + "\n");
			os.flush();
			os.writeBytes("exit\n");
			os.flush();
			try {
				suProcess.waitFor();
				if (suProcess.exitValue() != 255) {
					// TODO Code to run on success
					//showMessage("YES");
				}else {
					// TODO Code to run on unsuccessful
					//showMessage("No1");
				}
			} catch (InterruptedException e) {
				// TODO Code to run in interrupted exception
				//showMessage("No2");
			}
		} catch (IOException e) {
			// TODO Code to run in input/output exception
			//showMessage("No3");
		}
	}
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	
	
	public static abstract class BackgroundTaskClass {
		private Activity activity;
		public BackgroundTaskClass(Context activity) {
			this.activity = (Activity) activity;
		}
		private void startBackground() {
			new Thread(new Runnable() {
				public void run() {
					doInBackground();
					activity.runOnUiThread(new Runnable() {
						public void run() {
							onPostExecute();
						}
					});
				}
			}).start();
		}
		public void execute(){
			startBackground();
		}
		public abstract void doInBackground();
		public abstract void onPostExecute();
	}
	private void ProgressBar_Show(String title){
		prog = new ProgressDialog(VoiceActivity.this);
		prog.setMax(100);
		prog.setMessage(title);
		prog.setIndeterminate(true);
		prog.setCancelable(false);
		prog.setCanceledOnTouchOutside(false);
		prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
		prog.show();
	}
	private void ProgressBar_Dismiss(){
		if (prog != null){
			prog.dismiss();
		}
	}
	
	{
	}
	
	
	public void _copyFilePath2Uri(final String _OriginalFilePath) {
		File mfile6 = new File(_OriginalFilePath);
		    mfile1 = DocumentFile.fromTreeUri(this, uri2);
		                    
		    mfile1 = mfile1.createFile(getMimeType(_OriginalFilePath), Uri.parse(_OriginalFilePath).getLastPathSegment());
		    uri2 = mfile1.getUri();
		if (copyFilePath2Uri(VoiceActivity.this, mfile6, uri2)) {
				try {
						
				} catch (Exception e) {
						
				}
		} else {
				try {
						showMessage("失敗");
				} catch (Exception e) {
						
				}
		}
		
	}
	
	
	public void _loadInerstitialAd() {
		AdRequest inreq = new AdRequest.Builder().build();
		InterstitialAd.load(VoiceActivity.this,"ca-app-pub-3897977034034314/2804897827", inreq,new InterstitialAdLoadCallback() {
			@Override
			public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
				mInterstitialAd = interstitialAd;
				inerstitialLoaded = true;
			}
			@Override
			public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
				inerstitialLoaded = false;
				_loadInerstitialAd();
			}
		});
	}
	
	
	public void _Download_Plugins(final String _url, final String _path, final String _located) {
		try{
				DownloadManager.Request request = new DownloadManager.Request(Uri.parse( _url)); 
				request.setMimeType(this.getContentResolver().getType(Uri.parse(_url))); 
				String cookies = CookieManager.getInstance().getCookie(_url); 
				request.addRequestHeader("cookie", cookies); 
				//request.addRequestHeader("User-Agent", tab.getSettings().getUserAgentString());
				request.setDescription("正在下載插件"); 
				request.setTitle(URLUtil.guessFileName(_url,"",""));
				request.allowScanningByMediaScanner(); 
				request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); 
			    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
						Uri destinationUri = Uri.fromFile(new File("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/2-voice/" + _located + URLUtil.guessFileName(_url,"","")));
						request.setDestinationUri (destinationUri);
				}else{
						request.setDestinationInExternalPublicDir( _path.equals("")?Environment.DIRECTORY_DOWNLOADS:_path+_located, URLUtil.guessFileName(_url,"","")); 
				}
				DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE); dm.enqueue(request);
				showMessage("正在下載插件...");
		}catch(Exception e){
				showMessage(e.toString());
		}
	}
	
	
	public boolean _RootAccess() {
		if (RootTools.isRootAvailable()){
			//該手機已root
			try { //會跳出視窗
				java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(new String[]{"/system/bin/su","-c","cd / && ls"}).getInputStream()).useDelimiter("\\A");
				//true為有root且允許，false為有root但不允許
				return !(s.hasNext() ? s.next() : "").equals("");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
		}else {
			//該手機無root
			return false;
		}
	}
	
}