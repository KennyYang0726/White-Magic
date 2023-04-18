package com.aoveditor.phantomsneak;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import android.util.Log;
import android.app.Activity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import android.graphics.Color;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.HashMap;
import android.content.SharedPreferences;
import java.util.Calendar;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.Glide;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class FCMService extends FirebaseMessagingService {
		
		private static final String TAG = "FCMService";
		private SharedPreferences userFile;
		
		@Override
		public void onMessageReceived(RemoteMessage remoteMessage) {
				
				if (remoteMessage.getData().size() > 0) {
						
				}
				if (remoteMessage.getNotification() != null) {
						sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), remoteMessage.getNotification().getImageUrl() == null?null : remoteMessage.getNotification().getImageUrl().toString());
				}
		}
		
		@Override
		public void onNewToken(String token) {
				userFile = getSharedPreferences("userFile", Activity.MODE_PRIVATE);
				sendRegistrationToServer(token);
		}
		
		private void sendRegistrationToServer(String token) {
				
		}
		
		private void sendNotification(String title, String messageBody, String imageUrl) {
				Intent intent = new Intent(this, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
				String channelId = "channelID";
				Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
				final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
				.setSmallIcon(R.drawable.app_icon_r)
				.setContentTitle(title)
				.setContentText(messageBody)
				.setAutoCancel(true)
				.setSound(defaultSoundUri)
				.setContentIntent(pendingIntent);
				final NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
				
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
						NotificationChannel channel = new NotificationChannel(channelId, "FCM Service", NotificationManager.IMPORTANCE_HIGH);
						notificationManager.createNotificationChannel(channel);
						channel.enableLights(true);
						channel.setLightColor(Color.RED);
						channel.enableVibration(true);
						channel.setVibrationPattern(new long[]{100, 200, 300});
				}
				//notificationManager.notify((int)System.currentTimeMillis(), notificationBuilder.build());
				if (imageUrl == null) {
						notificationManager.notify((int)System.currentTimeMillis(), notificationBuilder.build());
				} else {
						Glide.with(getApplicationContext())
						.asBitmap()
						.load(imageUrl)
						.into(new CustomTarget<Bitmap>() {
								@Override
								public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
										notificationBuilder.setLargeIcon(resource);
										notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(resource));
										notificationManager.notify((int)System.currentTimeMillis(), notificationBuilder.build());
								}
								@Override
								public void onLoadCleared(@Nullable Drawable placeholder) {
										
								}
								@Override
								public void onLoadFailed(@Nullable Drawable errorDrawable) {
										super.onLoadFailed(errorDrawable);
										notificationManager.notify((int)System.currentTimeMillis(), notificationBuilder.build());
								}
						});
				}
		}
}
