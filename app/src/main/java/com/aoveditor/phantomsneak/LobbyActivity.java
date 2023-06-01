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

import android.app.Activity;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdSize;
import android.widget.LinearLayout.LayoutParams;

public class LobbyActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private boolean quit = false;
	private String skin_string = "";
	private String other_string = "";
	private String movie = "";
	private String sound = "";
	private  Uri uriA;
	private  Uri uriB;
	private  Uri muri;
	private  Uri uri2;
	private  DocumentFile mfile;
	private  DocumentFile mfile1;
	private String openquiz = "";
	private String quiz_content = "";
	private String quiz_url = "";
	private String anime1 = "";
	private String anime1_url = "";
	private String anime2 = "";
	private String anime2_url = "";
	private String anime3 = "";
	private String anime3_url = "";
	private String daogen1 = "";
	private String daogen2 = "";
	private String Nak3 = "";
	private String WAVE4 = "";
	private String NY_2021_5 = "";
	private String NY_2022_6 = "";
	private String AutoMan7 = "";
	private String PoolParty8 = "";
	private String AIC_2020_9 = "";
	private String KaRenNo_10 = "";
	private String TiLi_11 = "";
	private boolean haveSU = false;
	
	private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> map2 = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private ImageView imageview16;
	private LinearLayout linear9;
	private ImageView imageview17;
	private ScrollView vscroll1;
	private ImageView imageview15;
	private TextView textview1;
	private Button button1;
	private ImageView imageview14;
	private TextView textview2;
	private Button button2;
	private LinearLayout linear12;
	private LinearLayout linear10;
	private LinearLayout bannerAd;
	private Button button7;
	private Button button8;
	private Button button9;
	private Button button10;
	private Button button11;
	private Button button12;
	private Button button13;
	private Button button14;
	private Button button15;
	private Button button16;
	private Button button17;
	private Button button18;
	private Button button19;
	private Button button20;
	private ImageView imageview7;
	private ImageView imageview8;
	private ImageView imageview9;
	private ImageView imageview10;
	private ImageView imageview11;
	
	private Intent page = new Intent();
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	private AlertDialog.Builder dialog;
	private DatabaseReference string_skin = _firebase.getReference("string_skin");
	private ChildEventListener _string_skin_child_listener;
	private AlertDialog.Builder delete;
	private DatabaseReference lobby = _firebase.getReference("Lobby");
	private ChildEventListener _lobby_child_listener;
	private TimerTask t_delay;
	private AlertDialog.Builder quiz;
	private TimerTask t_internet;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.lobby);
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
		linear1 = findViewById(R.id.linear1);
		linear7 = findViewById(R.id.linear7);
		linear8 = findViewById(R.id.linear8);
		imageview16 = findViewById(R.id.imageview16);
		linear9 = findViewById(R.id.linear9);
		imageview17 = findViewById(R.id.imageview17);
		vscroll1 = findViewById(R.id.vscroll1);
		imageview15 = findViewById(R.id.imageview15);
		textview1 = findViewById(R.id.textview1);
		button1 = findViewById(R.id.button1);
		imageview14 = findViewById(R.id.imageview14);
		textview2 = findViewById(R.id.textview2);
		button2 = findViewById(R.id.button2);
		linear12 = findViewById(R.id.linear12);
		linear10 = findViewById(R.id.linear10);
		bannerAd = findViewById(R.id.bannerAd);
		button7 = findViewById(R.id.button7);
		button8 = findViewById(R.id.button8);
		button9 = findViewById(R.id.button9);
		button10 = findViewById(R.id.button10);
		button11 = findViewById(R.id.button11);
		button12 = findViewById(R.id.button12);
		button13 = findViewById(R.id.button13);
		button14 = findViewById(R.id.button14);
		button15 = findViewById(R.id.button15);
		button16 = findViewById(R.id.button16);
		button17 = findViewById(R.id.button17);
		button18 = findViewById(R.id.button18);
		button19 = findViewById(R.id.button19);
		button20 = findViewById(R.id.button20);
		imageview7 = findViewById(R.id.imageview7);
		imageview8 = findViewById(R.id.imageview8);
		imageview9 = findViewById(R.id.imageview9);
		imageview10 = findViewById(R.id.imageview10);
		imageview11 = findViewById(R.id.imageview11);
		net = new RequestNetwork(this);
		dialog = new AlertDialog.Builder(this);
		delete = new AlertDialog.Builder(this);
		quiz = new AlertDialog.Builder(this);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby");
				FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby");
				showMessage("刪除成功");
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				delete.setTitle("提示");
				delete.setIcon(R.drawable.downloadlogo);
				delete.setMessage("此按鈕用於還原大廳修改\n按下確認將還原，並且須下載一小部分大廳資源");
				delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
							uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F".concat(sound));
							try {
								try{
									        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
									     
									        } catch (FileNotFoundException e) {
									         
									    }             
							} catch (Exception e) {
								 
							}
							uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FISPDiff%2FLobbyMovie%2F".concat(movie));
							try {
								try{
									        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);
									    showMessage("還原成功");
									        } catch (FileNotFoundException e) {
									         
									    }             
							} catch (Exception e) {
								showMessage("失敗");
							}
						} else {
							FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/ISPDiff/LobbyMovie/".concat(movie));
							FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/".concat(sound));
							showMessage("還原成功");
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
		
		button7.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/DaoGen1.PS")) {
					_LinkStart("DaoGen1.PS");
				}
				else {
					DownloadHttpUrlConnection("https://" + daogen1, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "DaoGen1.PS");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		button8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/DaoGen2.PS")) {
					_LinkStart("DaoGen2.PS");
				}
				else {
					DownloadHttpUrlConnection("https://" + daogen2, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "DaoGen2.PS");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		button9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/Nakaroth.PS")) {
					_LinkStart("Nakaroth.PS");
				}
				else {
					DownloadHttpUrlConnection("https://" + Nak3, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "Nakaroth.PS");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		button10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/WAVE.PS")) {
					_LinkStart("WAVE.PS");
				}
				else {
					DownloadHttpUrlConnection("https://" + WAVE4, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "WAVE.PS");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		button11.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/newyear2021.PS")) {
					_LinkStart("newyear2021.PS");
				}
				else {
					DownloadHttpUrlConnection("https://" + NY_2021_5, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "newyear2021.PS");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		button12.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/newyear2022.PS")) {
					_LinkStart("newyear2022.PS");
				}
				else {
					DownloadHttpUrlConnection("https://" + NY_2022_6, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "newyear2022.PS");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		button13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/Ultraman.PS")) {
					_LinkStart("Ultraman.PS");
				}
				else {
					DownloadHttpUrlConnection("https://" + AutoMan7, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "Ultraman.PS");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		button14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/poolparty.PS")) {
					_LinkStart("poolparty.PS");
				}
				else {
					DownloadHttpUrlConnection("https://" + PoolParty8, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "poolparty.PS");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		button15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/AIC2020.PS")) {
					_LinkStart("AIC2020.PS");
				}
				else {
					DownloadHttpUrlConnection("https://" + AIC_2020_9, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "AIC2020.PS");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		button16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/KaLunNuo.PS")) {
					_LinkStart("KaLunNuo.PS");
				}
				else {
					DownloadHttpUrlConnection("https://" + KaRenNo_10, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "KaLunNuo.PS");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		button17.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/TiLi.PS")) {
					_LinkStart("TiLi.PS");
				}
				else {
					DownloadHttpUrlConnection("https://" + TiLi_11, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", "TiLi.PS");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		button18.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (anime1.contains("敬請期待")) {
					showMessage("敬請期待");
				}
				else {
					if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/" + anime1 + ".PS")) {
						_LinkStart(anime1 + ".PS");
					}
					else {
						DownloadHttpUrlConnection("https://" + anime1_url, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", anime1 + ".PS");
						showMessage("下載完成再次點擊以啟用");
					}
				}
			}
		});
		
		button19.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (anime2.contains("敬請期待")) {
					showMessage("敬請期待");
				}
				else {
					if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/" + anime2 + ".PS")) {
						_LinkStart(anime2 + ".PS");
					}
					else {
						DownloadHttpUrlConnection("https://" + anime2_url, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", anime2 + ".PS");
						showMessage("下載完成再次點擊以啟用");
					}
				}
			}
		});
		
		button20.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (anime3.contains("敬請期待")) {
					showMessage("敬請期待");
				}
				else {
					if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/" + anime3 + ".PS")) {
						_LinkStart(anime3 + ".PS");
					}
					else {
						DownloadHttpUrlConnection("https://" + anime3_url, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/", anime3 + ".PS");
						showMessage("下載完成再次點擊以啟用");
					}
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
		
		imageview9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				page.setClass(getApplicationContext(), VoiceActivity.class);
				startActivity(page);
				overridePendingTransition(0, 0);
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
		
		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				internet = 1;
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
		
		_lobby_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				lobby.addListenerForSingleValueEvent(new ValueEventListener() {
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
						anime1 = map2.get((int)0).get("anime1").toString();
						anime1_url = map2.get((int)0).get("anime1_url").toString();
						anime2 = map2.get((int)0).get("anime2").toString();
						anime2_url = map2.get((int)0).get("anime2_url").toString();
						anime3 = map2.get((int)0).get("anime3").toString();
						anime3_url = map2.get((int)0).get("anime3_url").toString();
						quiz_url = map2.get((int)0).get("quiz_url").toString();
						quiz_content = map2.get((int)0).get("quiz_content").toString();
						openquiz = map2.get((int)0).get("openquiz").toString();
						movie = map2.get((int)0).get("delete_movie").toString();
						sound = map2.get((int)0).get("delete_sound").toString();
						daogen1 = map2.get((int)0).get("1").toString();
						daogen2 = map2.get((int)0).get("2").toString();
						Nak3 = map2.get((int)0).get("3").toString();
						WAVE4 = map2.get((int)0).get("4").toString();
						NY_2021_5 = map2.get((int)0).get("5").toString();
						NY_2022_6 = map2.get((int)0).get("6").toString();
						AutoMan7 = map2.get((int)0).get("7").toString();
						PoolParty8 = map2.get((int)0).get("8").toString();
						AIC_2020_9 = map2.get((int)0).get("9").toString();
						KaRenNo_10 = map2.get((int)0).get("10").toString();
						TiLi_11 = map2.get((int)0).get("11").toString();
						button18.setText(anime1);
						button19.setText(anime2);
						button20.setText(anime3);
						if (openquiz.equals("1")) {
							quiz.setTitle("問卷");
							quiz.setIcon(R.drawable.downloadlogo);
							quiz.setMessage(quiz_content);
							quiz.setPositiveButton("前往", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									Intent browserintent = new Intent(Intent.ACTION_VIEW, Uri.parse(quiz_url));
									startActivity(browserintent);
								}
							});
							quiz.setNeutralButton("拒絕", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									
								}
							});
							quiz.create().show();
						}
						else {
							
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
		lobby.addChildEventListener(_lobby_child_listener);
	}
	
	private void initializeLogic() {
		if (!FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/3-lobby"))) {
			FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/3-lobby"));
		}
		quit = false;
		AdView banner3 = new AdView(LobbyActivity.this);
		banner3.setAdSize(AdSize.BANNER);
		banner3.setAdUnitId("ca-app-pub-3897977034034314/1209563365");
		AdRequest arbanner3 = new AdRequest.Builder().build();
		banner3.loadAd(arbanner3);
		LinearLayout.LayoutParams p3 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		bannerAd.addView(banner3,p3);
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
	
	
	public void _unzip(final String _fileZip, final String _destDir) {
		try
		        {
			            java.io.File outdir = new java.io.File(_destDir);
			            java.util.zip.ZipInputStream zin = new java.util.zip.ZipInputStream(new java.io.FileInputStream(_fileZip));
			            java.util.zip.ZipEntry entry;
			            String name, dir;
			            while ((entry = zin.getNextEntry()) != null)
			            {
				                
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
		        catch (java.io.IOException e)
		        {
			            e.printStackTrace();
			        }
		    }
	    private static void extractFile(java.util.zip.ZipInputStream in, java.io.File outdir, String name) throws java.io.IOException
	    {
		        byte[] buffer = new byte[4096];
		        java.io.BufferedOutputStream out = new java.io.BufferedOutputStream(new java.io.FileOutputStream(new java.io.File(outdir, name)));
		        int count = -1;
		        while ((count = in.read(buffer)) != -1)
		        out.write(buffer, 0, count);
		        out.close();
		    }
	    
	    private static void mkdirs(java.io.File outdir, String path)
	    {
		        java.io.File d = new java.io.File(outdir, path);
		        if(!d.exists())
		        d.mkdirs();
		    }
	    
	    private static String dirpart(String name)
	    {
		        int s = name.lastIndexOf(java.io.File.separatorChar);
		        return s == -1 ? null : name.substring(0, s);
	}
	
	
	public void _extra() {
	}
	public boolean copyFileFromUri2(Context context, Uri fileUri, Uri targetUri)
	    {
		        		InputStream fis = null;
				OutputStream fos = null;
		
				try {
						
			ContentResolver content = context.getContentResolver();
			            fis = content.openInputStream(fileUri);
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
	// url = file path or whatever suitable URL you want.
	public static String getMimeType(String url) {
		    String type = null;
		    String extension = MimeTypeMap.getFileExtensionFromUrl(url);
		    if (extension != null) {
			        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
			    }
		    return type;
	}
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
	
	private int internet;
	public void DownloadHttpUrlConnection(String Url, String Path, String FileName){
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
						if (FileName == ""){
							filename = URLUtil.guessFileName(Url, null, null);
						} else {
							filename = FileName;
						}
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
									prog.setTitle("下載中...");
									prog.setMessage("正在下載 " + filename + "\n下載進度 - " + (currentProgress/1000) + "%");
									prog.show();
								}
							});
							bout.write(data, 0, x);
						}
						bout.close();
						in.close();
						prog.dismiss();
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
	
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	{
	}
	
	
	public void _LinkStart(final String _fileName) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
			FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp");
			_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/".concat(_fileName), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp/");
			t_delay = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F".concat(sound));
							try {
								try{
									        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
									     
									        } catch (FileNotFoundException e) {
									         
									    }             
								uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
								_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp/Sound_DLC/Android/".concat(sound));
							} catch (Exception e) {
								 
							}
							uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FISPDiff%2FLobbyMovie%2F".concat(movie));
							try {
								try{
									        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);
									     
									        } catch (FileNotFoundException e) {
									         
									    }             
								uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FISPDiff%2FLobbyMovie%2F");
								_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp/ISPDiff/LobbyMovie/".concat(movie));
								showMessage("啟用成功");
								FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/tmp");
							} catch (Exception e) {
								showMessage("啟用失敗");
								showMessage("請確認是否下載遊戲大廳影片資源");
								showMessage("或刪除插件後重試");
							}
						}
					});
				}
			};
			_timer.schedule(t_delay, (int)(800));
		} else {
			_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/3-lobby/".concat(_fileName), "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/");
			showMessage("完成");
		}
	}
	
	
	public void _copyFilePath2Uri(final String _OriginalFilePath) {
		File mfile6 = new File(_OriginalFilePath);
		    mfile1 = DocumentFile.fromTreeUri(this, uri2);
		                    
		    mfile1 = mfile1.createFile(getMimeType(_OriginalFilePath), Uri.parse(_OriginalFilePath).getLastPathSegment());
		    uri2 = mfile1.getUri();
		if (copyFilePath2Uri(LobbyActivity.this, mfile6, uri2)) {
				try {
						
				} catch (Exception e) {
						
				}
		} else {
				try {
						showMessage("失敗");
						showMessage("請刪除插件後重試");
				} catch (Exception e) {
						
				}
		}
		
	}
	
}