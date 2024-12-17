package com.aoveditor.phantomsneak.Fragment5_TabFragmentAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.aoveditor.phantomsneak.Activity2_Fragment5_Other_Tab1;
import com.aoveditor.phantomsneak.Activity2_Fragment5_Other_Tab2;

public class Fragment5_TabFragmentAdapter extends FragmentStateAdapter {

    public Fragment5_TabFragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new Activity2_Fragment5_Other_Tab1();
        } else {
            return new Activity2_Fragment5_Other_Tab2();
        }
    }

    @Override
    public int getItemCount() {
        return 2; // 總共有 2 個頁面
    }

    @Override
    public long getItemId(int position) {
        // 返回唯一的 Fragment ID
        return position;
    }

    @Override
    public boolean containsItem(long itemId) {
        // 確保 ID 對應的 Fragment 存在
        return itemId >= 0 && itemId < getItemCount();
    }
}
