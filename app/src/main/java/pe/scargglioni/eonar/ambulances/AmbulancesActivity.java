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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pe.scargglioni.eonar.R;
import pe.scargglioni.eonar.data.Petition;
import pe.scargglioni.eonar.data.source.AmbulanceDataSource;
import pe.scargglioni.eonar.data.source.remote.AmbulanceFirebaseDataSource;
import pe.scargglioni.eonar.util.ActivityUtils;


public class AmbulancesActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    private AmbulancesPresenter mAmbulancePresenter;

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

        AmbulancesFragment ambulancesFragment = (AmbulancesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (ambulancesFragment == null) {
            ambulancesFragment = AmbulancesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), ambulancesFragment, R.id.contentFrame);
        }

        mAmbulancePresenter = new AmbulancesPresenter(AmbulanceFirebaseDataSource.getInstance(),ambulancesFragment);

        AmbulancesViewModel ambulancesViewModel = new AmbulancesViewModel(getApplicationContext(), mAmbulancePresenter);

        ambulancesFragment.setViewModel(ambulancesViewModel);

        if (savedInstanceState != null) {

        }

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
