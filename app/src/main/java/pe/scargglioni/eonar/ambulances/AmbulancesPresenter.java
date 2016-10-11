package pe.scargglioni.eonar.ambulances;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import pe.scargglioni.eonar.TempSingleton;
import pe.scargglioni.eonar.data.Ambulance;
import pe.scargglioni.eonar.data.Petition;
import pe.scargglioni.eonar.data.source.AmbulanceDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public class AmbulancesPresenter implements AmbulancesContract.Presenter {
    private final AmbulanceDataSource mAmbulanceDataSource;
    private final AmbulancesContract.View mAmbulanceView;

    public AmbulancesPresenter(@NonNull AmbulanceDataSource mAmbulanceDataSource, @NonNull AmbulancesContract.View mAmbulanceView) {
        this.mAmbulanceDataSource = checkNotNull(mAmbulanceDataSource);
        this.mAmbulanceView = checkNotNull(mAmbulanceView);
        mAmbulanceView.setPresenter(this);
    }

    @Override
    public void loadAmbulances() {
        mAmbulanceDataSource.getAmbulances(new AmbulanceDataSource.LoadAmbulancescCallback() {
            @Override
            public void onAmbulancesLoaded(List<Ambulance> ambulances) {
                mAmbulanceView.showAmbulances(ambulances);
            }

            @Override
            public void onDataNotAvailable() {
                Log.e("Mostando", "No data");
            }
        });

    }

    @Override
    public void createAlert() {

    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void createPetition() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference petitionReference = database.getReference("petitions");
        Petition newPetition;
        if (TempSingleton.getInstance().getmLastLocation() != null) {
            newPetition = new Petition(TempSingleton.getInstance().getmLastLocation().getLatitude(), TempSingleton.getInstance().getmLastLocation().getLongitude());
        } else {
            newPetition = new Petition(-12.0747384, -77.0843857);
        }
        newPetition.alarmTime = Calendar.getInstance().getTime();

        String key = petitionReference.push().getKey();
        petitionReference.child(key).setValue(newPetition);
    }

    @Override
    public void start() {
        loadAmbulances();
    }
}
