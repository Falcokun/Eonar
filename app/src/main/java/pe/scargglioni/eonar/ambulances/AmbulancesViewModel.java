package pe.scargglioni.eonar.ambulances;

import android.content.Context;
import android.databinding.BaseObservable;

import java.util.List;

import pe.scargglioni.eonar.BR;
import pe.scargglioni.eonar.data.Ambulance;


public class AmbulancesViewModel extends BaseObservable {
    private final AmbulancesContract.Presenter mPresenter;
    private Context mContext;
    private List<Ambulance> ambulances;

    public AmbulancesViewModel(Context mContext, AmbulancesContract.Presenter mPresenter) {
        this.mContext = mContext;
        this.mPresenter = mPresenter;
    }

    public void setAmbulances(List<Ambulance> ambulances) {
        this.ambulances = ambulances;
        notifyPropertyChanged(BR.actionHandler);
    }

    public String getAmbulances() {
        return ambulances.toString();
    }
}
