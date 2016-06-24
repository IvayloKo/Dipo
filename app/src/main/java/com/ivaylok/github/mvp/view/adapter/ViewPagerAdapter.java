package com.ivaylok.github.mvp.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ivaylok.github.mvp.view.fragment.FollowersFragment;
import com.ivaylok.github.mvp.view.fragment.FollowingsFragment;
import com.ivaylok.github.mvp.view.fragment.NewsFragment;
import com.ivaylok.github.mvp.view.fragment.RepositoriesFragment;
import com.ivaylok.github.mvp.view.fragment.StarsFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag = null;
        switch (position) {
            case 0:
                frag = new NewsFragment();
                break;
            case 1:
                frag = new RepositoriesFragment();
                break;
            case 2:
                frag = new StarsFragment();
                break;
            case 3:
                frag = new FollowersFragment();
                break;
            case 4:
                frag = new FollowingsFragment();
                break;
        }
        return frag;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "News";
                break;
            case 1:
                title = "Repositories";
                break;
            case 2:
                title = "Stars";
                break;
            case 3:
                title = "Followers";
                break;
            case 4:
                title = "Followings";
                break;
        }
        return title;
    }
}
