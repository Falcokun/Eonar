package pe.scargglioni.eonar.login;

import pe.scargglioni.eonar.BasePresenter;
import pe.scargglioni.eonar.BaseView;


public interface LoginContract {
    interface View extends BaseView<Presenter>{

    }
    interface Presenter extends BasePresenter{
        void result(int requestCode, int resultCode);
    }
}
