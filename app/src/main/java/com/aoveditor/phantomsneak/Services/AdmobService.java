package com.aoveditor.phantomsneak.Services;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class AdmobService {

    private final Context context;
    private InterstitialAd interstitialAd;
    private RewardedAd rewardedAd;

    public AdmobService(Context context) {
        this.context = context;
        MobileAds.initialize(context, initializationStatus -> Log.d("AdmobService", "AdMob initialized"));
    }

    // 初始化橫幅廣告
    public void loadBannerAd(AdView adView) {
        if (adView == null) {
            Log.e("AdmobService", "AdView is null, cannot load banner ad");
            return;
        }
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    // 加載插頁廣告
    public void loadInterstitialAd(String adUnitId) {
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(context, adUnitId, adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                AdmobService.this.interstitialAd = interstitialAd;
                Log.d("AdmobService", "Interstitial Ad Loaded");
            }
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                Log.e("AdmobService", "Failed to load interstitial ad: " + loadAdError.getMessage());
            }
        });
    }

    // 顯示插頁廣告，並在顯示後自動加載下一個
    public void showInterstitialAd(String adUnitId) {
        if (interstitialAd != null && context instanceof Activity) {
            interstitialAd.show((Activity) context);

            interstitialAd.setFullScreenContentCallback(new com.google.android.gms.ads.FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    Log.d("AdmobService", "Interstitial Ad Dismissed");
                    // 廣告顯示後自動加載下一個
                    loadInterstitialAd(adUnitId);
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull com.google.android.gms.ads.AdError adError) {
                    Log.e("AdmobService", "Failed to show interstitial ad: " + adError.getMessage());
                }
            });
        } else {
            Log.d("AdmobService", "Interstitial Ad not loaded yet");
        }
    }

    // 加載獎勵廣告
    public void loadRewardedAd(String adUnitId) {
        AdRequest adRequest = new AdRequest.Builder().build();
        RewardedAd.load(context, adUnitId, adRequest, new RewardedAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                AdmobService.this.rewardedAd = rewardedAd;
                Log.d("AdmobService", "Rewarded Ad Loaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                Log.e("AdmobService", "Failed to load rewarded ad: " + loadAdError.getMessage());
            }
        });
    }

    // 顯示獎勵廣告
    public void showRewardedAd(OnAdCompletionListener listener) {
        if (rewardedAd != null && context instanceof Activity) {
            rewardedAd.show((Activity) context, rewardItem -> {
                Log.d("AdmobService", "Reward earned: " + rewardItem.getType());
                listener.onAdCompleted(true); // 廣告完成觸發
            });

            rewardedAd.setFullScreenContentCallback(new com.google.android.gms.ads.FullScreenContentCallback() {
                @Override
                public void onAdDismissedFullScreenContent() {
                    Log.d("AdmobService", "Rewarded Ad Dismissed");
                    listener.onAdCompleted(false); // 廣告未完成觸發
                }
            });

        } else {
            Log.d("AdmobService", "Rewarded Ad not loaded yet");
        }
    }

    public boolean isRewardedAdLoaded() {
        return rewardedAd != null;
    }

    public interface OnAdCompletionListener {
        void onAdCompleted(boolean completed);
    }
}

