package pe.scargglioni.eonar.ambulances;


import android.support.annotation.NonNull;

import pe.scargglioni.eonar.login.LoginContract;

import static com.google.common.base.Preconditions.checkNotNull;

public class AmbulancePresenter implements AmbulanceContract.Presenter {
    private  final AmbulanceContract.View mAmbulanceView;

    public AmbulancePresenter(@NonNull AmbulanceContract.View mAmbulanceView){
        this.mAmbulanceView= checkNotNull(mAmbulanceView);
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void start() {

    }
}
