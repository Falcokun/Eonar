package pe.scargglioni.eonar;


import android.location.Location;

import com.google.android.gms.location.LocationServices;

public class TempSingleton {
    private static TempSingleton INSTANCE;
    private  Location mLastLocation;


    public static TempSingleton getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TempSingleton();
        }
        return INSTANCE;
    }

    ;

    private TempSingleton() {

    }

    public Location getmLastLocation() {
        return mLastLocation;
    }

    public void setmLastLocation(Location mLastLocation) {
        this.mLastLocation = mLastLocation;
    }
}
