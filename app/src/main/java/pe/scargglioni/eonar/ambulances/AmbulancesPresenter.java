package pe.scargglioni.eonar.ambulances;


import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class AmbulancesPresenter implements AmbulancesContract.Presenter {
    private final AmbulancesContract.View mAmbulanceView;

    public AmbulancesPresenter(@NonNull AmbulancesContract.View mAmbulanceView) {
        this.mAmbulanceView = checkNotNull(mAmbulanceView);
        mAmbulanceView.setPresenter(this);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void start() {

    }
}
