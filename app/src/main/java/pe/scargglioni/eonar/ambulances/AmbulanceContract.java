package pe.scargglioni.eonar.ambulances;


import pe.scargglioni.eonar.BasePresenter;
import pe.scargglioni.eonar.BaseView;

public interface AmbulanceContract {

    interface View extends BaseView<Presenter>{

    }

    interface Presenter extends BasePresenter{
        void result(int requestCode, int resultCode);
    }
}
