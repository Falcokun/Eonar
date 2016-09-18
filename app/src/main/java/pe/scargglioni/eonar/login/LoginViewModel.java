package pe.scargglioni.eonar.login;

import android.content.Context;
import android.databinding.BaseObservable;


public class LoginViewModel extends BaseObservable {
    private final LoginContract.Presenter mPresenter;

    private Context mContext;

    public LoginViewModel(Context mContext, LoginContract.Presenter mPresenter) {
        this.mContext = mContext;
        this.mPresenter = mPresenter;
    }
}
