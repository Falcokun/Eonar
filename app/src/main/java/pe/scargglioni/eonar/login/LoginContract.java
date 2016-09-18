package pe.scargglioni.eonar.login;

import pe.scargglioni.eonar.BasePresenter;
import pe.scargglioni.eonar.BaseView;


public interface LoginContract {
    interface View extends BaseView<Presenter>{
        void openMainActivity();

    }
    interface Presenter extends BasePresenter{
        void doLogin();
        void result(int requestCode, int resultCode);
    }
}
