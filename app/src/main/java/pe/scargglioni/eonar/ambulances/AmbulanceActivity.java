package pe.scargglioni.eonar.ambulances;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import pe.scargglioni.eonar.R;
import pe.scargglioni.eonar.util.ActivityUtils;


public class AmbulanceActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    private AmbulancePresenter mAmbulancePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ambulances_act);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        AmbulanceFragment ambulanceFragment = (AmbulanceFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (ambulanceFragment == null) {
            ambulanceFragment = AmbulanceFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), ambulanceFragment, R.id.contentFrame);
        }

        mAmbulancePresenter = new AmbulancePresenter(ambulanceFragment);

        AmbulanceViewModel ambulanceViewModel = new AmbulanceViewModel(getApplicationContext(), mAmbulancePresenter);

        ambulanceFragment.setViewModel(ambulanceViewModel);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    default:
                        break;
                }
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }
}
