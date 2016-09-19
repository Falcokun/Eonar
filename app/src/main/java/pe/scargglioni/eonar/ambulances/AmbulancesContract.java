package pe.scargglioni.eonar.ambulances;


import java.util.List;

import pe.scargglioni.eonar.BasePresenter;
import pe.scargglioni.eonar.BaseView;
import pe.scargglioni.eonar.data.Ambulance;

public interface AmbulancesContract {

    interface View extends BaseView<Presenter>{
        void showAmbulances(List<Ambulance> ambulances);

    }

    interface Presenter extends BasePresenter{
        void loadAmbulances();
        void createAlert();
        void result(int requestCode, int resultCode);
    }
}
