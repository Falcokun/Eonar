package pe.scargglioni.eonar.login;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import pe.scargglioni.eonar.R;
import pe.scargglioni.eonar.TempSingleton;
import pe.scargglioni.eonar.data.Ambulance;
import pe.scargglioni.eonar.util.ActivityUtils;

public class LoginActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    private LoginPresenter mLoginPresenter;
    GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_act);

        //Defaults
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        //DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        //Fragment


        LoginFragment loginFragment =
                (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (loginFragment == null) {
            // Create the fragment
            loginFragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), loginFragment, R.id.contentFrame);
        }


        mLoginPresenter = new LoginPresenter(loginFragment);

        LoginViewModel tasksViewModel = new LoginViewModel(getApplicationContext(), mLoginPresenter);

        loginFragment.setViewModel(tasksViewModel);

        // Load previously saved state, if available.
        if (savedInstanceState != null) {

        }

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(@Nullable Bundle bundle) {
                            Log.e("Test", "conecto");
                            if (ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                                    ActivityCompat.checkSelfPermission(LoginActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                               String[] permisions  = new String[1];
                                permisions[0] = Manifest.permission.ACCESS_FINE_LOCATION;
                                ActivityCompat.requestPermissions(LoginActivity.this,permisions ,10);
                            }
                            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                                    mGoogleApiClient);
                            TempSingleton.getInstance().setmLastLocation(mLastLocation);

                        }

                        @Override
                        public void onConnectionSuspended(int i) {
                            TempSingleton.getInstance().setmLastLocation(null);
                        }
                    })
                    .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                            Log.e("Test", connectionResult.getErrorMessage());
                            TempSingleton.getInstance().setmLastLocation(null);
                        }
                    })
                    .addApi(LocationServices.API)
                    .build();
        }

    }

    @Override
    protected void onStop() {
        mGoogleApiClient.connect();
        super.onStop();
    }

    @Override
    protected void onStart() {
        mGoogleApiClient.disconnect();
        super.onStart();
    }

    @Override
        protected void onSaveInstanceState(Bundle outState) {
            //outState.putSerializable(CURRENT_FILTERING_KEY, mTasksPresenter.getFiltering());

            super.onSaveInstanceState(outState);
        }

        private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

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
