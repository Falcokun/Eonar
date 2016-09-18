package pe.scargglioni.eonar.ambulances;

import android.content.Context;
import android.databinding.BaseObservable;


public class AmbulancesViewModel extends BaseObservable {
    private final AmbulancesContract.Presenter mPresenter;
    private Context mContext;

    public AmbulancesViewModel(Context mContext, AmbulancesContract.Presenter mPresenter) {
        this.mContext = mContext;
        this.mPresenter = mPresenter;
    }
}
