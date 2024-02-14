package com.aoveditor.phantomsneak;

import android.Manifest;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.*;
import android.os.*;
import android.util.*;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.*;
import java.util.*;
import android.os.Handler;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import com.stericson.RootTools.RootTools;
import rikka.shizuku.Shizuku;

import com.aoveditor.phantomsneak.Utils.FileUtil;
import com.aoveditor.phantomsneak.network.RequestNetwork;


public class MainActivity extends AppCompatActivity {
	
	private String txtJson = "";
	private boolean regionVN = false;
	private AlertDialog.Builder no;
	public static boolean haveSU = false;
	private RequestNetwork net;
	private RequestNetwork.RequestListener _net_request_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			//initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			//initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		no = new AlertDialog.Builder(this);
		net = new RequestNetwork(this);

		if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/Shizuku")) //針對 僅允許一次 Shizuku 授權 (SUI)
			/**重要，如果關閉服務，需要先判斷ping才能檢測是否granted，否則異常*/
			if (Shizuku.pingBinder()) { //關閉 shizuku 服務就 ping 不到了
				//Toast.makeText(getApplicationContext(), "ping成功", Toast.LENGTH_SHORT).show();
				if (!(Shizuku.checkSelfPermission() == PackageManager.PERMISSION_GRANTED)) {
					//Toast.makeText(getApplicationContext(), "檢測權限失敗", Toast.LENGTH_SHORT).show();
					FileUtil.deleteFile("/data/user/0/com.aoveditor.phantomsneak/Shizuku");
				} else {
					//Toast.makeText(getApplicationContext(), "檢測權限成功", Toast.LENGTH_SHORT).show();
				}
			} else {
				//Toast.makeText(getApplicationContext(), "ping失敗", Toast.LENGTH_SHORT).show();
				Toast.makeText(getApplicationContext(), "檢測到關閉Shizuk服務\n稍後請重新選擇存取模式", Toast.LENGTH_SHORT).show();
				FileUtil.deleteFile("/data/user/0/com.aoveditor.phantomsneak/Shizuku");
			}


		if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/Shizuku") && FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/SAF")) {
			//若同時存在2判斷文件，都刪掉要求重新選擇
			FileUtil.deleteFile("/data/user/0/com.aoveditor.phantomsneak/Shizuku");
			FileUtil.deleteFile("/data/user/0/com.aoveditor.phantomsneak/SAF");
		}

		new Thread() {
			public void run() {
				Looper.prepare();
				if (_RootAccess()) {
					haveSU = true;
					initializeLogic();
				} else {
					haveSU = false;
					initializeLogic();
				}
				Looper.loop();
			}
		}.start();

		_net_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		FileUtil.makeDir("/data/user/0/com.aoveditor.phantomsneak/files/texture/1-Home");
		FileUtil.makeDir("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice");
		FileUtil.makeDir("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other");
		new JsonTask().execute("http://ip-api.com/json");

		ConnectivityManager connectivityManager = (android.net.ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		Network activeNet = connectivityManager.getActiveNetwork();
		NetworkCapabilities netCaps = connectivityManager.getNetworkCapabilities(activeNet);
		boolean vpn = netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
		new Handler().postDelayed(new Runnable() {
			public void run() {
				if ((!Locale.getDefault().toLanguageTag().contains("VN"))&&(!vpn)&&(!regionVN)&&checkInstallation(MainActivity.this, "com.garena.game.kgtw")){ //地區非越南 + 不開VPN + 手機語言非越南文 + 有下載 傳說對決(台服) 才可使用
					//判斷是否進入ChooseUtil
					Intent intent = new Intent();

					if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {//11以上才需要選擇，否則一樣去Home

						if (!(FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/Shizuku")) && !(FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/SAF")) && !(FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/SU"))) {
							//3無，直接跳轉
							intent.setClass(MainActivity.this, ChooseUtilActivity.class);
							startActivity(intent);
						} else if ((FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/SU")) && !haveSU) { //有Su但沒權限
							intent.setClass(MainActivity.this, ChooseUtilActivity.class);
							startActivity(intent);
						} else { //應該沒問題了
							intent.setClass(MainActivity.this, HomeActivity.class);
							if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/Shizuku"))
								ChooseUtilActivity.Method = "Shizuku";
							else if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/SAF"))
								ChooseUtilActivity.Method = "SAF";
							else if (FileUtil.isExistFile("/data/user/0/com.aoveditor.phantomsneak/SU"))
								ChooseUtilActivity.Method = "SU";
							startActivity(intent);
						}
					} else {
						intent.setClass(MainActivity.this, HomeActivity.class);
						startActivity(intent);
					}
					finish();
					overridePendingTransition(0, 0);
				} else {
					getDialog();
				}
			}
		}, 3000);
	}
	
	
	@Override
	public void onBackPressed() {
		if (true) { //為了讓這裡沒法按返回退出應用
		}
	}

	private void getDialog() {
		no.setIcon(R.drawable.app_icon_r);
		no.setTitle("提醒");
		no.setMessage("①裝置語言非越南文\n②不得開啟VPN\n③檢測地區非越南\n④下載並開啟過 傳說對決(台服)\n    (com.garena.game.kgtw)\n\n由於您可能違反上述條件之一，所以不得進入app");
		no.setCancelable(false);
		no.setPositiveButton("確認", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface _dialog, int _which) {
				finishAffinity();
			}
		});
		no.create().show();
	}
	private class JsonTask extends AsyncTask<String, String, String> {
		protected void onPreExecute() {
			super.onPreExecute();
		}
			
		protected String doInBackground(String... params) {
			Looper.prepare();
			HttpURLConnection connection = null;
			BufferedReader reader = null;
			try {
				URL url = new URL(params[0]);
				connection = (HttpURLConnection) url.openConnection();
				connection.connect();
				InputStream stream = connection.getInputStream();
				reader = new BufferedReader(new InputStreamReader(stream));
				StringBuffer buffer = new StringBuffer();
				String line = "";
				while ((line = reader.readLine()) != null) {
					buffer.append(line+"\n");
					Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
				}
				return buffer.toString();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (connection != null) {
					connection.disconnect();
				}
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Looper.loop();
			return null;
		}
			
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			txtJson = (result);
			if (txtJson.contains("Vietnam")) {
				regionVN = true;
			} else {
				regionVN = false;
			}
		}
	}

	public static boolean checkInstallation(Context context, String packageName) {
		// on below line creating a variable for package manager.
		PackageManager pm = context.getPackageManager();
		try {
			// on below line getting package info
			pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
			// on below line returning true if package is installed.
			return true;
		} catch (PackageManager.NameNotFoundException e) {
			// returning false if package is not installed on device.
			return false;
		}
	}

	private boolean _RootAccess() {
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
		} else {
			//該手機無root
			return false;
		}
	}

}
