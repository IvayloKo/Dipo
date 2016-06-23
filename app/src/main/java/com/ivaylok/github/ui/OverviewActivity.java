package com.ivaylok.github.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ivaylok.github.R;
import com.ivaylok.github.model.RepoAdapter;
import com.ivaylok.github.model.RepoResponse;
import com.ivaylok.github.service.RepoViewInterface;

import java.util.List;

import rx.Observable;

public class OverviewActivity extends AppCompatActivity {

    ViewPager pager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        configViewPager();
    }

    private void configViewPager() {

        pager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        // Fragment manager to add fragment in viewpager we
        // will pass object of Fragment manager to adapter
        FragmentManager manager = getSupportFragmentManager();

        //Object of PagerAdapter passing fragment manager object as a parameter
        //of constructor of PagerAdapter
        PagerAdapter adapter = new com.ivaylok.github.utils.PagerAdapter(manager);

        //set Adapter to view pager
        pager.setAdapter(adapter);

        //set tablayout with viewpager
        tabLayout.setupWithViewPager(pager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //adding functionality to tab and viewpager to manage each other
        // when a page is changed or when a tab is selected
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //Setting tab from adapter
        tabLayout.setTabsFromPagerAdapter(adapter);

    }

}
