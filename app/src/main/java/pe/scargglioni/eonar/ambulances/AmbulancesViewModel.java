package pe.scargglioni.eonar.ambulances;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import pe.scargglioni.eonar.BR;
import pe.scargglioni.eonar.data.Ambulance;


public class AmbulancesViewModel extends BaseObservable {
    private final AmbulancesContract.Presenter mPresenter;
    private Context mContext;
    private List<Ambulance> ambulanceList;

    public AmbulancesViewModel(Context mContext, AmbulancesContract.Presenter mPresenter) {
        this.mContext = mContext;
        this.mPresenter = mPresenter;
        ambulanceList = new ArrayList<>();
    }

    public void setAmbulances(List<Ambulance> ambulances) {
        this.ambulanceList = ambulances;
        notifyPropertyChanged(BR.ambulances);
    }

    @Bindable
    public String getAmbulances() {
        return ambulanceList.toString();
    }
}
