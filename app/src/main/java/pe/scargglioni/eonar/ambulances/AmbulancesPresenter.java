package pe.scargglioni.eonar.ambulances;


import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import pe.scargglioni.eonar.data.Ambulance;
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
                Log.e("Mostando","No data");
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
    public void start() {
        loadAmbulances();
    }
}
