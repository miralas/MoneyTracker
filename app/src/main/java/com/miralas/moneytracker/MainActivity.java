package com.miralas.moneytracker;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FloatingActionButton fab;

    private static final int PAGE_EXPENSES = 0;
    private static final int PAGE_INCOMES = 1;
    private static final int PAGE_BALANCE = 2;

    private static final int ADD_ITEM_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        MainPagesAdapter adapter = new MainPagesAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case PAGE_EXPENSES:
                        fab.show();
                        break;
                    case PAGE_INCOMES:
                        fab.show();
                        break;
                    case PAGE_BALANCE:
                        fab.hide();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // move fab to main activity(coordinator layout)
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // явный Intent
                Intent intent = new Intent(getBaseContext(), AddItemActivity.class);
                startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);

                // неявный Intent
//                Intent intent = new Intent();
//                intent.setAction(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://pikabu.ru/"));
//                startActivity(intent);
            }
        });
    }

}
