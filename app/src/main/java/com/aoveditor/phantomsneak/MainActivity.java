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


public class MainActivity extends AppCompatActivity {
	
	private String txtJson = "";
	private boolean regionVN = false;
	private AlertDialog.Builder no;
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
		no = new AlertDialog.Builder(this);
		net = new RequestNetwork(this);
		
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
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
			Network activeNet = connectivityManager.getActiveNetwork();
			NetworkCapabilities netCaps = connectivityManager.getNetworkCapabilities(activeNet);
			boolean vpn = netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
			new Handler().postDelayed(new Runnable() {
				public void run() {
					if ((!Locale.getDefault().toLanguageTag().contains("VN"))&&(!vpn)&&(!regionVN)){ //地區非越南 + 不開VPN + 手機語言非越南文 才可使用
						Intent intent = new Intent();
						intent.setClass(MainActivity.this, HomeActivity.class);
						MainActivity.this.startActivity(intent);
						MainActivity.this.finish();
						MainActivity.this.overridePendingTransition(0, 0);
					} else {
						getDialog();
					}
				}
			}, 3000);
		} else {
			showMessage("安卓版本過低，請更換一台試試");
			finishAffinity();
		}
		
	}
	
	
	@Override
	public void onBackPressed() {
		if (true) { //為了讓這裡沒法按返回退出應用
		}
	}

	private void getDialog() {
		no.setIcon(R.drawable.app_icon_r);
		no.setTitle("提醒");
		no.setMessage("①裝置語言非越南文\n②不得開啟VPN\n③檢測地區非越南\n\n由於您可能違反上述條件之一，所以不得進入app");
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

	public void showMessage(String s) {
		Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
	}

}
