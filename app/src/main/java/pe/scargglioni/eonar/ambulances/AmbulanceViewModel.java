package pe.scargglioni.eonar.ambulances;

import android.content.Context;
import android.databinding.BaseObservable;


public class AmbulanceViewModel extends BaseObservable {
    private final AmbulanceContract.Presenter mPresenter;
    private Context mContext;

    public AmbulanceViewModel(Context mContext, AmbulanceContract.Presenter mPresenter) {
        this.mContext = mContext;
        this.mPresenter = mPresenter;
    }
}
