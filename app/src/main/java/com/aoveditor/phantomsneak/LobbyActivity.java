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
import com.google.android.gms.ads.RequestConfiguration;
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


public class OtherActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private boolean quit = false;
	private  Uri muri;
	private  Uri uri2;
	private  DocumentFile mfile;
	private  DocumentFile mfile1;
	private String wiro = "";
	private String Volkath = "";
	private String game_ver = "";
	private  Uri uriA;
	private  Uri uriB;
	private  Uri uriC;
	private  Uri uriD;
	private  Uri uriE;
	private  Uri uriF;
	private String tower = "";
	private String monster = "";
	private String soldier = "";
	private String skin_string = "";
	private String lobby_string = "";
	
	private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> map2 = new ArrayList<>();
	
	private LinearLayout linear16;
	private ScrollView vscroll1;
	private LinearLayout linear7;
	private ImageView imageview15;
	private TextView textview1;
	private Button button13;
	private LinearLayout linear8;
	private ImageView imageview12;
	private LinearLayout linear9;
	private ImageView imageview17;
	private ImageView imageview18;
	private LinearLayout linear10;
	private ImageView imageview25;
	private ImageView imageview23;
	private LinearLayout linear11;
	private ImageView imageview20;
	private ImageView imageview21;
	private LinearLayout linear12;
	private ImageView imageview32;
	private ImageView imageview27;
	private LinearLayout linear13;
	private ImageView imageview36;
	private ImageView imageview33;
	private LinearLayout linear18;
	private LinearLayout bannerAd;
	private Button button1;
	private ImageView imageview13;
	private Button button2;
	private Button button3;
	private ImageView imageview19;
	private Button button4;
	private Button button5;
	private ImageView imageview22;
	private Button button6;
	private Button button7;
	private ImageView imageview26;
	private Button button8;
	private Button button9;
	private ImageView imageview29;
	private Button button10;
	private Button button16;
	private ImageView imageview35;
	private Button button17;
	private ImageView imageview7;
	private ImageView imageview8;
	private ImageView imageview9;
	private ImageView imageview10;
	private ImageView imageview11;
	
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	private AlertDialog.Builder dialog;
	private Intent page = new Intent();
	private DatabaseReference string_skin = _firebase.getReference("string_skin");
	private ChildEventListener _string_skin_child_listener;
	private TimerTask t_delay;
	private DatabaseReference Other = _firebase.getReference("Other");
	private ChildEventListener _Other_child_listener;
	private AlertDialog.Builder delete;
	private TimerTask t_internet;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.other);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);
		
		List<String> testDeviceIds = Arrays.asList();
		MobileAds.setRequestConfiguration(new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build());
		
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
		linear16 = findViewById(R.id.linear16);
		vscroll1 = findViewById(R.id.vscroll1);
		linear7 = findViewById(R.id.linear7);
		imageview15 = findViewById(R.id.imageview15);
		textview1 = findViewById(R.id.textview1);
		button13 = findViewById(R.id.button13);
		linear8 = findViewById(R.id.linear8);
		imageview12 = findViewById(R.id.imageview12);
		linear9 = findViewById(R.id.linear9);
		imageview17 = findViewById(R.id.imageview17);
		imageview18 = findViewById(R.id.imageview18);
		linear10 = findViewById(R.id.linear10);
		imageview25 = findViewById(R.id.imageview25);
		imageview23 = findViewById(R.id.imageview23);
		linear11 = findViewById(R.id.linear11);
		imageview20 = findViewById(R.id.imageview20);
		imageview21 = findViewById(R.id.imageview21);
		linear12 = findViewById(R.id.linear12);
		imageview32 = findViewById(R.id.imageview32);
		imageview27 = findViewById(R.id.imageview27);
		linear13 = findViewById(R.id.linear13);
		imageview36 = findViewById(R.id.imageview36);
		imageview33 = findViewById(R.id.imageview33);
		linear18 = findViewById(R.id.linear18);
		bannerAd = findViewById(R.id.bannerAd);
		button1 = findViewById(R.id.button1);
		imageview13 = findViewById(R.id.imageview13);
		button2 = findViewById(R.id.button2);
		button3 = findViewById(R.id.button3);
		imageview19 = findViewById(R.id.imageview19);
		button4 = findViewById(R.id.button4);
		button5 = findViewById(R.id.button5);
		imageview22 = findViewById(R.id.imageview22);
		button6 = findViewById(R.id.button6);
		button7 = findViewById(R.id.button7);
		imageview26 = findViewById(R.id.imageview26);
		button8 = findViewById(R.id.button8);
		button9 = findViewById(R.id.button9);
		imageview29 = findViewById(R.id.imageview29);
		button10 = findViewById(R.id.button10);
		button16 = findViewById(R.id.button16);
		imageview35 = findViewById(R.id.imageview35);
		button17 = findViewById(R.id.button17);
		imageview7 = findViewById(R.id.imageview7);
		imageview8 = findViewById(R.id.imageview8);
		imageview9 = findViewById(R.id.imageview9);
		imageview10 = findViewById(R.id.imageview10);
		imageview11 = findViewById(R.id.imageview11);
		net = new RequestNetwork(this);
		dialog = new AlertDialog.Builder(this);
		delete = new AlertDialog.Builder(this);
		
		button13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/4-other"));
				FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/4-other"));
				showMessage("刪除完成");
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/wiro.PS")) {
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
						_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/wiro.PS", "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/");
						t_delay = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FAges%2FPrefab_Characters%2FPrefab_Hero%2FActor_194_Actions.pkg.bytes")));
										try {
											try{
												        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
												     
												        } catch (FileNotFoundException e) {
												         
												    }             
											uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FAges%2FPrefab_Characters%2FPrefab_Hero%2F")));
											_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Resources/".concat(game_ver.concat("/Ages/Prefab_Characters/Prefab_Hero/Actor_194_Actions.pkg.bytes")));
										} catch (Exception e) {
											 
										}
										uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2Fassetbundle%2Ficon%2Fhero%2F194_sulie_icon.assetbundle")));
										try {
											try{
												        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
												     
												        } catch (FileNotFoundException e) {
												         
												    }             
											uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2Fassetbundle%2Ficon%2Fhero%2F")));
											_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Resources/".concat(game_ver.concat("/assetbundle/icon/hero/194_sulie_icon.assetbundle")));
										} catch (Exception e) {
											 
										}
										uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_low_raw_h.assetbundle");
										try {
											try{
												        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);
												     
												        } catch (FileNotFoundException e) {
												         
												    }             
											uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F");
											_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Extra/2019.V2/assetbundle/show/hero/194_sulie_show_base_low_raw_h.assetbundle");
										} catch (Exception e) {
											 
										}
										uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_raw_h.assetbundle");
										try {
											try{
												        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);
												     
												        } catch (FileNotFoundException e) {
												         
												    }             
											uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F");
											_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Extra/2019.V2/assetbundle/show/hero/194_sulie_show_base_raw_h.assetbundle");
										} catch (Exception e) {
											 
										}
										uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FLanguages%2FCHT_Garena_TW%2FlanguageMap_Newbie.txt")));
										try {
											try{
												        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);
												     
												        } catch (FileNotFoundException e) {
												         
												    }             
											uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FLanguages%2FCHT_Garena_TW%2F")));
											_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Resources/".concat(game_ver.concat("/Languages/CHT_Garena_TW/languageMap_Newbie.txt")));
										} catch (Exception e) {
											 
										}
										uriC = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FCDNImage%2FHeroHeadIcon%2FE08074BD2C22D7294436E68F0AEA0E90");
										try {
											try{
												        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriC);
												     
												        } catch (FileNotFoundException e) {
												         
												    }             
											uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FCDNImage%2FHeroHeadIcon%2F");
											_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/CDNImage/HeroHeadIcon/E08074BD2C22D7294436E68F0AEA0E90");
										} catch (Exception e) {
											 
										}
										uriD = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FHero_Wiro_SFX.bnk");
										try {
											try{
												        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriD);
												     
												        } catch (FileNotFoundException e) {
												         
												    }             
											uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
											_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Extra/2019.V2/Sound_DLC/Android/Hero_Wiro_SFX.bnk");
										} catch (Exception e) {
											 
										}
										uriE = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_Show.bnk");
										try {
											try{
												        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriE);
												     
												        } catch (FileNotFoundException e) {
												         
												    }             
											uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2F");
											_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_Show.bnk");
										} catch (Exception e) {
											 
										}
										uriF = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_VO.bnk");
										try {
											try{
												        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriF);
												     
												        } catch (FileNotFoundException e) {
												         
												    }             
											uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2F");
											_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_VO.bnk");
											showMessage("啟用成功");
											FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/CDNImage");
											FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Extra");
											FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/Resources");
										} catch (Exception e) {
											showMessage("啟用失敗");
										}
									}
								});
							}
						};
						_timer.schedule(t_delay, (int)(100));
					} else {
						_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/wiro.PS", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/");
						showMessage("完成");
					}
				}
				else {
					DownloadHttpUrlConnection("https://" + wiro, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/", "wiro.PS");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				delete.setTitle("提示");
				delete.setIcon(R.drawable.downloadlogo);
				delete.setMessage("此按鈕用於還原國動維羅修改\n按下確認將還原，並且須下載一小部分大廳資源");
				delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
							uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources");
							try {
								try{
									        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
									     
									        } catch (FileNotFoundException e) {
									         
									    }             
							} catch (Exception e) {
								showMessage("還原失敗");
								showMessage("請按遊戲登入畫面左上的一鍵恢復");
							}
							uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_low_raw_h.assetbundle");
							try {
								try{
									        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);
									     
									        } catch (FileNotFoundException e) {
									         
									    }             
							} catch (Exception e) {
								 
							}
							uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2Fassetbundle%2Fshow%2Fhero%2F194_sulie_show_base_raw_h.assetbundle");
							try {
								try{
									        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);
									     
									        } catch (FileNotFoundException e) {
									         
									    }             
							} catch (Exception e) {
								 
							}
							uriC = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FCDNImage%2FHeroHeadIcon%2FE08074BD2C22D7294436E68F0AEA0E90");
							try {
								try{
									        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriC);
									     
									        } catch (FileNotFoundException e) {
									         
									    }             
							} catch (Exception e) {
								 
							}
							uriD = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FHero_Wiro_SFX.bnk");
							try {
								try{
									        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriD);
									     
									        } catch (FileNotFoundException e) {
									         
									    }             
							} catch (Exception e) {
								 
							}
							uriE = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_Show.bnk");
							try {
								try{
									        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriE);
									     
									        } catch (FileNotFoundException e) {
									         
									    }             
							} catch (Exception e) {
								 
							}
							uriF = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FChinese(Taiwan)%2FHero_Wiro_VO.bnk");
							try {
								try{
									        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriF);
									     
									        } catch (FileNotFoundException e) {
									         
									    }             
							} catch (Exception e) {
								 
							}
						} else {
							FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
							FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/CDNImage/HeroHeadIcon/E08074BD2C22D7294436E68F0AEA0E90");
							FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Hero_Wiro_SFX.bnk");
							FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_Show.bnk");
							FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Chinese(Taiwan)/Hero_Wiro_VO.bnk");
						}
						showMessage("還原成功");
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
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/volkath.PS")) {
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
						_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/volkath.PS", "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/");
						t_delay = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FAges%2FPrefab_Characters%2FPrefab_Hero%2FActor_529_Actions.pkg.bytes")));
										try {
											try{
												        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
												     
												        } catch (FileNotFoundException e) {
												         
												    }             
											uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FAges%2FPrefab_Characters%2FPrefab_Hero%2F")));
											_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/".concat(game_ver.concat("/Ages/Prefab_Characters/Prefab_Hero/Actor_529_Actions.pkg.bytes")));
										} catch (Exception e) {
											 
										}
										uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FPrefab_Characters%2FActor_529_Infos.pkg.bytes")));
										try {
											try{
												        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);
												     
												        } catch (FileNotFoundException e) {
												         
												    }             
											uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FPrefab_Characters%2F")));
											_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/".concat(game_ver.concat("/Prefab_Characters/Actor_529_Infos.pkg.bytes")));
											showMessage("啟用成功");
											FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/".concat(game_ver));
										} catch (Exception e) {
											showMessage("啟用失敗");
										}
									}
								});
							}
						};
						_timer.schedule(t_delay, (int)(100));
					} else {
						_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/volkath.PS", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/");
						showMessage("完成");
					}
				}
				else {
					DownloadHttpUrlConnection("https://" + Volkath, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/4-other/", "volkath.PS");
					showMessage("下載完成再次點擊以啟用");
				}
			}
		});
		
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				delete.setTitle("提示");
				delete.setIcon(R.drawable.downloadlogo);
				delete.setMessage("此按鈕用於還原安格列變身修改\n按下確認將還原，無須於大廳下載資源");
				delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
							uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources");
							try {
								try{
									        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
									     
									        } catch (FileNotFoundException e) {
									         
									    }             
							} catch (Exception e) {
								showMessage("還原失敗");
								showMessage("請按遊戲登入畫面左上的一鍵恢復");
							}
						} else {
							FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
						}
						showMessage("還原成功");
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
		
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Other("https://" + tower, "Prefab_Organ.pkg.bytes", "Res");
			}
		});
		
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				delete.setTitle("提示");
				delete.setIcon(R.drawable.downloadlogo);
				delete.setMessage("此按鈕用於還原次元防禦塔修改\n按下確認將還原，無須下載任何大廳資源");
				delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						Other("https://cdn.discordapp.com/attachments/842221289464004608/1017783442680860752/Prefab_Organ.pkg.bytes", "Prefab_Organ.pkg.bytes", "Res");
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
				Other("https://" + soldier, "Prefab_Soldier.pkg.bytes", "Res");
			}
		});
		
		button8.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				delete.setTitle("提示");
				delete.setIcon(R.drawable.downloadlogo);
				delete.setMessage("此按鈕用於還原次元兵線修改\n按下確認將還原，無須下載任何大廳資源");
				delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						Other("https://cdn.discordapp.com/attachments/842221289464004608/1017783442924118077/Prefab_Soldier.pkg.bytes", "Prefab_Soldier.pkg.bytes", "Res");
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
		
		button9.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Other("https://" + monster, "Prefab_Monster.pkg.bytes", "Res");
			}
		});
		
		button10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				delete.setTitle("提示");
				delete.setIcon(R.drawable.downloadlogo);
				delete.setMessage("此按鈕用於還原次元紅藍buff修改\n按下確認將還原，無須下載任何大廳資源");
				delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						Other("https://cdn.discordapp.com/attachments/842221289464004608/1017783442349502594/Prefab_Monster.pkg.bytes", "Prefab_Monster.pkg.bytes", "Res");
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
		
		button16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				Other("https://cdn.discordapp.com/attachments/842221289464004608/1022879773078327306/Hero_Wisp_SFX.bnk", "Hero_Wisp_SFX.bnk", "Extra");
			}
		});
		
		button17.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
					uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2FHero_Wisp_SFX.bnk");
					try {
						try{
							DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
						} catch (FileNotFoundException e) {
						}
					} catch (Exception e) {
					}
				}else{
					FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/Hero_Wisp_SFX.bnk");
				}
				showMessage("還原成功");
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
				}
else if (skin_string.contains("停用")) {
						showMessage("無法使用");
				}
else if (skin_string.contains("更新")) {
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
		
		imageview10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (lobby_string.equals("") || internet == 0) {
						showMessage("初始化中..");
				}
else if (lobby_string.contains("停用")) {
						showMessage("無法使用");
				}
else if (lobby_string.contains("更新")) {
						showMessage("更新中");
				} else {
						page.setClass(getApplicationContext(), LobbyActivity.class);
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
						lobby_string = map1.get((int)0).get("2-lobby").toString();
						game_ver = map1.get((int)0).get("ver").toString();
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
		
		_Other_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				Other.addListenerForSingleValueEvent(new ValueEventListener() {
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
						wiro = map2.get((int)0).get("wiro").toString();
						Volkath = map2.get((int)0).get("volkath").toString();
						tower = map2.get((int)0).get("tower").toString();
						monster = map2.get((int)0).get("monster").toString();
						soldier = map2.get((int)0).get("solider").toString();
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
		Other.addChildEventListener(_Other_child_listener);
	}
	
	private void initializeLogic() {
		if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/wiro.png")) {
			imageview12.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/wiro.png", 1024, 1024));
		}
		else {
			DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304245035180092/wiro.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview12);
		}
		if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/volkath.png")) {
			imageview18.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/volkath.png", 1024, 1024));
		}
		else {
			DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304244775129169/volkath.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview18);
		}
		if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/tower.png")) {
			imageview23.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/tower.png", 1024, 1024));
		}
		else {
			DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304244506701924/tower.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview23);
		}
		if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/soldier.png")) {
			imageview21.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/soldier.png", 1024, 1024));
		}
		else {
			DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304244242456656/soldier.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview21);
		}
		if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/buff.png")) {
			imageview27.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/buff.png", 1024, 1024));
		}
		else {
			DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304243965628467/buff.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview27);
		}
		if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/zhadanren.png")) {
			imageview33.setImageBitmap(FileUtil.decodeSampleBitmapFromPath("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/zhadanren.png", 1024, 1024));
		}
		else {
			DLC("https://cdn.discordapp.com/attachments/842221289464004608/1074304245303619635/zhadanren.png", "/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other/", imageview33);
		}
		if (!FileUtil.isExistFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/4-other"))) {
			FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/4-other"));
		}
		quit = false;
		AdView banner4 = new AdView(OtherActivity.this);
		banner4.setAdSize(AdSize.BANNER);
		banner4.setAdUnitId("ca-app-pub-3897977034034314/1209563365");
		AdRequest arbanner4 = new AdRequest.Builder().build();
		banner4.loadAd(arbanner4);
		LinearLayout.LayoutParams p4 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		bannerAd.addView(banner4,p4);
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
		catch (java.io.IOException e)
		{
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
	

	// url = file path or whatever suitable URL you want.
	public static String getMimeType(String url) {
		    String type = null;
		    String extension = MimeTypeMap.getFileExtensionFromUrl(url);
		    if (extension != null) {
			        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
			    }
		    return type;
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
	
	public void Other(String Url, String Name, String Located){
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
						String filename = "";
						String Path = "";
						filename = Name;
						//filename = URLUtil.guessFileName(Url, null, null);
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
							Path = "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/";
						}else{
							if (Located.contains("Res")){
								Path = "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Prefab_Characters/";
							} else if (Located.contains("Extra")){
								Path = "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Extra/2019.V2/Sound_DLC/Android/";
							}
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
									prog.setMessage("下載中...請稍後... ");
									prog.show();
								}
							});
							bout.write(data, 0, x);
						}
						bout.close();
						in.close();
						prog.dismiss();
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
							if (Located.contains("Res")){
								uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F" + game_ver + "%2FPrefab_Characters%2F" + filename);
								try {
									try{
										DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
									} catch (FileNotFoundException e) {
									}
									uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F" + game_ver + "%2FPrefab_Characters%2F");
									_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/" + filename);
									FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/" + filename);
									showMessage("成功");
								} catch (Exception e) {
									uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F" + game_ver + "%2FPrefab_Characters%2F");
									_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/" + filename);
									FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/" + filename);
									showMessage("成功");
								}
							} else if (Located.contains("Extra")){
								uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F" + filename);
								try {
									try{
										DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
									} catch (FileNotFoundException e) {
									}
									uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
									_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/" + filename);
									FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/" + filename);
									showMessage("成功");
								} catch (Exception e) {
									uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FExtra%2F2019.V2%2FSound_DLC%2FAndroid%2F");
									_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/" + filename);
									FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/" + filename);
									showMessage("成功");
								}
							}
						} else {
							showMessage("成功");
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
	
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}

	
	public void _copyFilePath2Uri(final String _OriginalFilePath) {
		File mfile6 = new File(_OriginalFilePath);
		    mfile1 = DocumentFile.fromTreeUri(this, uri2);
		                    
		    mfile1 = mfile1.createFile(getMimeType(_OriginalFilePath), Uri.parse(_OriginalFilePath).getLastPathSegment());
		    uri2 = mfile1.getUri();
		if (copyFilePath2Uri(OtherActivity.this, mfile6, uri2)) {
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
	
}
