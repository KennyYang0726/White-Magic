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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import android.os.Handler;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class MainActivity extends AppCompatActivity {

	private String txtJson = "";
	private boolean regionVN = false;

	private ImageView imageview1;

	private Intent intent = new Intent();

	private OnCompleteListener fcm_onCompleteListener;
	private AlertDialog.Builder no;

	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		MobileAds.initialize(this);

		if (ContextCompat.checkSelfPermission(this,
				Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
				|| ContextCompat.checkSelfPermission(this,
						Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.READ_EXTERNAL_STORAGE,
					Manifest.permission.WRITE_EXTERNAL_STORAGE }, 1000);
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
		imageview1 = findViewById(R.id.imageview1);
		no = new AlertDialog.Builder(this);

		fcm_onCompleteListener = new OnCompleteListener<InstanceIdResult>() {
			@Override
			public void onComplete(Task<InstanceIdResult> task) {
				final boolean _success = task.isSuccessful();
				final String _token = task.getResult().getToken();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";

			}
		};
	}

	private void initializeLogic() {
		FileUtil.makeDir("/data/user/0/com.aoveditor.phantomsneak/files/texture/1-Home");
		FileUtil.makeDir("/data/user/0/com.aoveditor.phantomsneak/files/texture/3-Voice");
		FileUtil.makeDir("/data/user/0/com.aoveditor.phantomsneak/files/texture/5-Other");
		new JsonTask().execute("http://ip-api.com/json");

		ConnectivityManager connectivityManager = (android.net.ConnectivityManager) getSystemService(
				Context.CONNECTIVITY_SERVICE);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			Network activeNet = connectivityManager.getActiveNetwork();
			NetworkCapabilities netCaps = connectivityManager.getNetworkCapabilities(activeNet);
			boolean vpn = netCaps.hasTransport(NetworkCapabilities.TRANSPORT_VPN);
			new Handler().postDelayed(new Runnable() {
				public void run() {
					if ((!Locale.getDefault().toLanguageTag().contains("VN")) && (!vpn) && (!regionVN)) { // ??????????????? + ??????VPN + ???????????????????????? ????????????
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
			showMessage("??????????????????????????????????????????");
			finishAffinity();
		}

	}

	@Override
	public void onBackPressed() {
		if (true) {

		}
	}


	private void getDialog() {
		no.setIcon(R.drawable.app_icon_r);
		no.setTitle("??????");
		no.setMessage("???????????????????????????\n???????????????VPN\n????????????????????????\n\n????????????????????????????????????????????????????????????app");
		no.setCancelable(false);
		no.setPositiveButton("??????", new DialogInterface.OnClickListener() {
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
					buffer.append(line + "\n");
					Log.d("Response: ", "> " + line); // here u ll get whole response...... :-)
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

	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}

}
