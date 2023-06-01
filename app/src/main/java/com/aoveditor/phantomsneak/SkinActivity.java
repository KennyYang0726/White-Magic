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
import android.content.ClipboardManager;
import java.security.Key;
import android.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.net.HttpURLConnection;

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
import java.lang.Process;

public class SkinActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private boolean quit = false;
	private String lobby_string = "";
	private String other_string = "";
	private String skin_name = "";
	private String plugin = "";
	private  Uri uriA;
	private String game_ver = "";
	private String video = "";
	private String language = "";
	private  DocumentFile mfile;
	private  DocumentFile mfile1;
	private  Uri uri2;
	private  Uri muri;
	Uri uriB;
	Uri uriC;
	Uri uriD;
	public static final String IV = "1234567890148763";
	private static Key KEY;
	private String desurl = "";
	private boolean inerstitialLoaded = false;
	public InterstitialAd mInterstitialAd;
	private boolean haveSU = false;
	
	private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> map2 = new ArrayList<>();
	private ArrayList<String> files_in_skin = new ArrayList<>();
	private ArrayList<String> file_list = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear7;
	private LinearLayout linear8;
	private ImageView imageview16;
	private LinearLayout linear9;
	private ImageView imageview17;
	private LinearLayout linear14;
	private Button button3;
	private LinearLayout linear10;
	private LinearLayout linear13;
	private LinearLayout bannerAd;
	private ImageView imageview15;
	private TextView textview1;
	private Button button1;
	private ImageView imageview14;
	private TextView textview2;
	private Button button2;
	private VideoView videoview1;
	private LinearLayout linear11;
	private LinearLayout linear12;
	private TextView textview3;
	private TextView textview5;
	private TextView textview4;
	private TextView textview6;
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
	private DatabaseReference skin_plugin = _firebase.getReference("skin_pluging");
	private ChildEventListener _skin_plugin_child_listener;
	private AlertDialog.Builder delete;
	private TimerTask t;
	private TimerTask t_internet;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.skin);
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
		linear14 = findViewById(R.id.linear14);
		button3 = findViewById(R.id.button3);
		linear10 = findViewById(R.id.linear10);
		linear13 = findViewById(R.id.linear13);
		bannerAd = findViewById(R.id.bannerAd);
		imageview15 = findViewById(R.id.imageview15);
		textview1 = findViewById(R.id.textview1);
		button1 = findViewById(R.id.button1);
		imageview14 = findViewById(R.id.imageview14);
		textview2 = findViewById(R.id.textview2);
		button2 = findViewById(R.id.button2);
		videoview1 = findViewById(R.id.videoview1);
		MediaController videoview1_controller = new MediaController(this);
		videoview1.setMediaController(videoview1_controller);
		linear11 = findViewById(R.id.linear11);
		linear12 = findViewById(R.id.linear12);
		textview3 = findViewById(R.id.textview3);
		textview5 = findViewById(R.id.textview5);
		textview4 = findViewById(R.id.textview4);
		textview6 = findViewById(R.id.textview6);
		imageview7 = findViewById(R.id.imageview7);
		imageview8 = findViewById(R.id.imageview8);
		imageview9 = findViewById(R.id.imageview9);
		imageview10 = findViewById(R.id.imageview10);
		imageview11 = findViewById(R.id.imageview11);
		net = new RequestNetwork(this);
		dialog = new AlertDialog.Builder(this);
		delete = new AlertDialog.Builder(this);
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/".concat(skin_name))) {
					if (inerstitialLoaded) {
						mInterstitialAd.show(SkinActivity.this);
					}
					else {
						
					}
					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
						if (haveSU) {
							ProgressBar_Show("請稍等...");
							new BackgroundTaskClass(SkinActivity.this){
								@Override
								public void doInBackground() {
									Looper.prepare();
									FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/Resources");
									_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/"+(skin_name), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/Resources/");
								}
								@Override
								public void onPostExecute(){
									ProgressBar_Dismiss();
									
									ProgressBar_Show("啟用中...");
									new BackgroundTaskClass(SkinActivity.this){
										@Override
										public void doInBackground() {
											Looper.prepare();
											//root複製
											CopyWithhaveSU("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/Resources", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources", true);
										}
										@Override
										public void onPostExecute(){
											ProgressBar_Dismiss();
											showMessage("完成");
											FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/Resources");
										}
									}.execute();
									//結束
								}
							}.execute();
							//結束
						}
						else {
							try {
								_startSkin();
							} catch (Exception e) {
								showMessage(e.getMessage());
							}
						}
					} else {
						_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/".concat(skin_name), "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/");
						showMessage("完成");
					}
				}
				else {
					FileUtil.deleteFile(FileUtil.getPackageDataDir(getApplicationContext()).concat("/1-skin"));
					FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/1-skin"));
					Download("https://" + desurl, "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/");
					showMessage("下載完成再次點擊以啟用");
					repair("https://" + language);
				}
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin");
				FileUtil.makeDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin");
				showMessage("刪除成功");
				textview5.setText("無造型插件");
				button3.setText("下載插件");
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				delete.setTitle("提醒");
				delete.setIcon(R.drawable.downloadlogo);
				delete.setMessage("按下確認後，還原遊戲造型資源\n需進入遊戲重新下載前置資源");
				delete.setCancelable(false);
				delete.setPositiveButton("確認", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
							try {
								uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources");
								try {
									try{
										        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
										     
										        } catch (FileNotFoundException e) {
										         
										    }             
								} catch (Exception e) {
									 
								}
								showMessage("還原成功");
								page.setClass(getApplicationContext(), HomeActivity.class);
								startActivity(page);
								overridePendingTransition(0, 0);
							} catch (Exception e) {
								showMessage("還原失敗");
								showMessage("請按遊戲設置的「一鍵恢復」");
							}
						} else {
							FileUtil.deleteFile("/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources");
							showMessage("還原完成");
							page.setClass(getApplicationContext(), HomeActivity.class);
							startActivity(page);
							overridePendingTransition(0, 0);
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
		
		videoview1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer _mediaPlayer) {
				
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
						lobby_string = map1.get((int)0).get("2-lobby").toString();
						other_string = map1.get((int)0).get("3-other").toString();
						game_ver = map1.get((int)0).get("ver").toString();
						language = map1.get((int)0).get("language").toString();
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
		
		_skin_plugin_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				skin_plugin.addListenerForSingleValueEvent(new ValueEventListener() {
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
						skin_name = map2.get((int)0).get("plugin").toString();
						plugin = map2.get((int)0).get("zurl").toString();
						video = map2.get((int)0).get("video_url").toString();
						textview6.setText(skin_name);
						videoview1.setMediaController(null);
						videoview1.setVideoURI(Uri.parse(video));
						videoview1.start();
						Desceiption();
						if (FileUtil.isExistFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/".concat(skin_name))) {
							button3.setText("啟用");
						}
						else {
							button3.setText("下載插件");
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
		skin_plugin.addChildEventListener(_skin_plugin_child_listener);
	}
	
	private void initializeLogic() {
		
		quit = false;
		_loadInerstitialAd();
		FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/", files_in_skin);
		AdView banner1 = new AdView(SkinActivity.this);
		banner1.setAdSize(AdSize.BANNER);
		banner1.setAdUnitId("ca-app-pub-3897977034034314/1209563365");
		AdRequest arbanner1 = new AdRequest.Builder().build();
		banner1.loadAd(arbanner1);
		LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		bannerAd.addView(banner1,p1);
		if (files_in_skin.size() == 0) {
			textview5.setText("無造型插件");
		}
		else {
			textview5.setText(Uri.parse(files_in_skin.get((int)(0))).getLastPathSegment());
		}
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
	private int count;
	private int internet;
	public void Desceiption(){
		try{
			String stringKey = "F0UKlJdM5s8bY6uksC8763==";
			byte[] encodedKey = Base64.decode(stringKey, Base64.DEFAULT);
			KEY = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
			IvParameterSpec iv = new IvParameterSpec(IV.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, KEY, iv);
			byte[] bytes = cipher.doFinal(Base64.decode(plugin, Base64.DEFAULT));
			desurl = new String(bytes);
			//showMessage (desurl);
		}catch (Exception e){}
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
	
	public void Download(String Url, String Path){
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
							final int currentProgress = (int) ((((double)downloadedFileSize) / ((double)completeFileSize)) * 100000d);
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									prog.setTitle("下載造型插件中...");
									prog.setMessage("正在下載 " + filename + "\n下載進度：" + (currentProgress/1000) + "%");
									prog.show();
									textview5.setText(skin_name);
									button3.setText("啟用");
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
	
	public void repair(String Url){
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
						String Path;
						filename = URLUtil.guessFileName(Url, null, null);
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
							Path = "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/";
						}else{
							Path = "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Languages/CHT_Garena_TW/";
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
								}
							});
							bout.write(data, 0, x);
						}
						bout.close();
						in.close();
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
							if (haveSU){
								CopyWithhaveSU("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt", "/storage/emulated/0/Android/data/com.garena.game.kgtw/files/Resources/" + game_ver + "/Languages/CHT_Garena_TW/languageMap.txt", true);
								FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt");
							} else { //noSU
								uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FLanguages%2FCHT_Garena_TW%2FlanguageMap.txt")));
								try {
									try{
										DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
									} catch (FileNotFoundException e) {
									}
									uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FLanguages%2FCHT_Garena_TW%2F")));
									_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt");
									FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt");
								} catch (Exception e) {
									uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F".concat(game_ver.concat("%2FLanguages%2FCHT_Garena_TW%2F")));
									_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt");
									FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/languageMap.txt");
								}
							}
						}
					} else {
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
	//背景運作Prog
	private ProgressDialog prog2;
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
		prog2 = new ProgressDialog(SkinActivity.this);
		prog2.setMax(100);
		prog2.setMessage(title);
		prog2.setIndeterminate(true);
		prog2.setCancelable(false);
		prog2.setCanceledOnTouchOutside(false);
		prog2.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		prog2.show();
	}
	
	private void ProgressBar_Dismiss(){
		if (prog2 != null){
			prog2.dismiss();
		}
	}
	{
	}
	
	
	public void _startSkin() {
		FileUtil.makeDir(FileUtil.getPackageDataDir(getApplicationContext()).concat("/1-skin/tmp"));
		_unzip("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/".concat(skin_name), "/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/");
		uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FActor%2F"+"heroSkin.bytes");
		try {
				try{
						DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
				} catch (FileNotFoundException e) {
						
				}
				uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FActor%2F");
				_copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Actor/heroSkin.bytes");
		} catch (Exception e) {
				
		}
		uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FCharacter%2F"+"ResCharacterComponent.bytes");
		try {
				try{
						DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);
				} catch (FileNotFoundException e) {
						
				}
				uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FCharacter%2F");
			    _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Character/ResCharacterComponent.bytes");
		} catch (Exception e) {
				
		}
		uriB = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FCharacter%2F"+"ResComponentShow.bytes");
		try {
				try{
						DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriB);
				} catch (FileNotFoundException e) {
						
				}
				uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FCharacter%2F");
			    _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Character/ResComponentShow.bytes");
		} catch (Exception e) {
				
		}
		uriC = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSkill%2F"+"liteBulletCfg.bytes");
		try {
				try{
						DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriC);
				} catch (FileNotFoundException e) {
						
				}
				uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSkill%2F");
			    _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Skill/liteBulletCfg.bytes");
		} catch (Exception e) {
				
		}
		uriC = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSkill%2F"+"skillmark.bytes");
		try {
				try{
						DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriC);
				} catch (FileNotFoundException e) {
						
				}
				uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSkill%2F");
			    _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Skill/skillmark.bytes");
		} catch (Exception e) {
				
		}
		uriD = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F"+"BattleBank.bytes");
		try {
				try{
						DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriD);
				} catch (FileNotFoundException e) {
						
				}
				uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F");
			    _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Sound/BattleBank.bytes");
		} catch (Exception e) {
				
		}
		uriD = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F"+"ChatSound.bytes");
		try {
				try{
						DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriD);
				} catch (FileNotFoundException e) {
						
				}
				uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F");
			    _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Sound/ChatSound.bytes");
		} catch (Exception e) {
				
		}
		uriD = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F"+"HeroSound.bytes");
		try {
				try{
						DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriD);
				} catch (FileNotFoundException e) {
						
				}
				uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F");
			    _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Sound/HeroSound.bytes");
		} catch (Exception e) {
				
		}
		uriD = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F"+"LobbyBank.bytes");
		try {
				try{
						DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriD);
				} catch (FileNotFoundException e) {
						
				}
				uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F");
			    _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Sound/LobbyBank.bytes");
		} catch (Exception e) {
				
		}
		uriD = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F"+"LobbySound.bytes");
		try {
				try{
						DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriD);
				} catch (FileNotFoundException e) {
						
				}
				uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FDatabin%2FClient%2FSound%2F");
			    _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Databin/Client/Sound/LobbySound.bytes");
		} catch (Exception e) {
				
		}
		
		FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Ages/Prefab_Characters/Prefab_Hero/", file_list);
		count=file_list.size();
		final ProgressDialog prog = new ProgressDialog(SkinActivity.this);
		prog.setIcon(R.drawable.downloadlogo);
		prog.setMax(100);
		prog.setIndeterminate(true);
		prog.setCancelable(false);
		prog.setCanceledOnTouchOutside(false);
		prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
		new Thread(new Runnable() {
				@Override
				public void run() {
						for(int j = 0; j < (int)(file_list.size()); j++) {
								try {
										uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FAges%2FPrefab_Characters%2FPrefab_Hero%2F"+Uri.parse(file_list.get((count - 1))).getLastPathSegment());
										try{
												DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
										} catch (FileNotFoundException e) {
							
										}
								} catch (Exception e) {
						
								}
								uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FAges%2FPrefab_Characters%2FPrefab_Hero%2F");
					            _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Ages/Prefab_Characters/Prefab_Hero/" + Uri.parse(file_list.get(count - 1)).getLastPathSegment());
								count--;
								runOnUiThread(new Runnable() {
										@Override
										public void run() {
												prog.setTitle("2 / 4 步驟啟用中...");
												prog.setMessage("\n剩餘檔案數量："+count);
												prog.show();
												if (count == 0) {
														prog.dismiss();
														file_list.clear();
																
														FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/AssetRefs/Hero/", file_list);
														count=file_list.size();
														final ProgressDialog prog = new ProgressDialog(SkinActivity.this);
														prog.setIcon(R.drawable.downloadlogo);
														prog.setMax(100);
														prog.setIndeterminate(true);
														prog.setCancelable(false);
														prog.setCanceledOnTouchOutside(false);
														prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
														prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
														new Thread(new Runnable() {
																@Override
																public void run() {
																		for(int k = 0; k < (int)(file_list.size()); k++) {
																				try {
																						uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FAssetRefs%2FHero%2F"+Uri.parse(file_list.get((count - 1))).getLastPathSegment());
																						try{
																								DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
																						} catch (FileNotFoundException e) {
																								
																						}
																				} catch (Exception e) {
												
																				}
																				uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FAssetRefs%2FHero%2F");
											                                    _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/AssetRefs/Hero/" + Uri.parse(file_list.get(count - 1)).getLastPathSegment());
																				count--;
																				runOnUiThread(new Runnable() {
																						@Override
																						public void run() {
																								prog.setTitle("3 / 4 步驟啟用中...");
																								prog.setMessage("\n剩餘檔案數量："+count);
																								prog.show();
																								if (count == 0) {
																										prog.dismiss();
																										file_list.clear();
																												
																										FileUtil.listDir("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Prefab_Characters/", file_list);
																										count=file_list.size();
																										final ProgressDialog prog = new ProgressDialog(SkinActivity.this);
																										prog.setIcon(R.drawable.downloadlogo);
																										prog.setMax(100);
																										prog.setIndeterminate(true);
																										prog.setCancelable(false);
																										prog.setCanceledOnTouchOutside(false);
																										prog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
																										prog.getWindow().setBackgroundDrawableResource(R.drawable.dialog);
																										new Thread(new Runnable() {
																												@Override
																												public void run() {
																														for(int l = 0; l < (int)(file_list.size()); l++) {
																																try {
																																		uriA = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FPrefab_Characters%2F"+Uri.parse(file_list.get((count - 1))).getLastPathSegment());
																																		try{
																																				DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), uriA);
																																		} catch (FileNotFoundException e) {
																			
																																		}
																																} catch (Exception e) {
																																	
																																}
																																uri2 = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw/document/primary%3AAndroid%2Fdata%2Fcom.garena.game.kgtw%2Ffiles%2FResources%2F"+game_ver+"%2FPrefab_Characters%2F");
																	                                                            _copyFilePath2Uri("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp/"+game_ver+"/Prefab_Characters/" + Uri.parse(file_list.get(count - 1)).getLastPathSegment());
																																count--;
																																runOnUiThread(new Runnable() {
																																		@Override
																																		public void run() {
																																				prog.setTitle("4 / 4 步驟啟用中...");
																																				prog.setMessage("\n剩餘檔案數量："+count);
																																				prog.show();
																																				if (count == 0) {
																																						prog.dismiss();
																																						file_list.clear();
																																						FileUtil.deleteFile("/storage/emulated/0/Android/data/com.aoveditor.phantomsneak/files/1-skin/tmp");
																																						showMessage("完成");
																																				}
																																		}
																																});
																														}
																												}
																										}).start();
																								}
																						}
																				});
																		}
																}
														}).start();
												}
										}
								});
						}
				}
		}).start();
	}
	
	
	public void _copyFilePath2Uri(final String _OriginalFilePath) {
		File mfile6 = new File(_OriginalFilePath);
		    mfile1 = DocumentFile.fromTreeUri(this, uri2);
		                    
		    mfile1 = mfile1.createFile(getMimeType(_OriginalFilePath), Uri.parse(_OriginalFilePath).getLastPathSegment());
		    uri2 = mfile1.getUri();
		if (copyFilePath2Uri(SkinActivity.this, mfile6, uri2)) {
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
		InterstitialAd.load(SkinActivity.this,"ca-app-pub-3897977034034314/2804897827", inreq,new InterstitialAdLoadCallback() {
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