package domon.cn.timer.ui.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import domon.cn.timer.App;
import domon.cn.timer.R;
import domon.cn.timer.data.CategoriesData;
import domon.cn.timer.data.Config;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.start_btn)
    Button mStartBtn;

    @Bind(R.id.main_record_tv)
    TextView mRecordTimeTv;

    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;

    @Bind(R.id.nav_view)
    NavigationView navigationView;

    @OnClick(R.id.start_btn)
    void onClickStartBtn() {
        RecordActivity.actionStart(this);
        if (Config.getCategoryName() != null) {
            Config.setCategoryName(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initDataBase();

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().getItem(0).setChecked(true);
    }

    private void initDataBase() {
        initCategroyDb();
        setUserImei();
    }

    private void initCategroyDb() {
        long count = App.liteOrm.queryCount(CategoriesData.class);
        if (count == 0) {
            String[] categroyNames = new String[]{
                    getString(R.string.category_read),
                    getString(R.string.category_rest),
                    getString(R.string.category_snacks),
                    getString(R.string.category_sport),
                    getString(R.string.category_eat),
                    getString(R.string.category_sleep),
                    getString(R.string.category_study),
                    getString(R.string.category_walk),
                    getString(R.string.category_breath),
                    getString(R.string.category_work)};
            for (int i = 0; i < categroyNames.length; i++) {
                CategoriesData categoriesData = new CategoriesData(categroyNames[i]);
                App.liteOrm.save(categoriesData);
                Logger.i("add categories");
            }
        }
    }

    private void setUserImei() {
        String imei = ((TelephonyManager) getSystemService(TELEPHONY_SERVICE)).getDeviceId();
        Config.setUserImei(imei);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_main) {
        } else if (id == R.id.nav_statistical) {
            StatisticalActivity.actionStart(this);
        } else if (id == R.id.nav_setting) {
            SettingActivity.actionStart(this);
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
