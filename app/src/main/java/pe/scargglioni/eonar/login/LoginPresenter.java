package pe.scargglioni.eonar.login;


import android.support.annotation.NonNull;

import static com.google.common.base.Preconditions.checkNotNull;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;

    public LoginPresenter(@NonNull LoginContract.View mLoginView) {
        this.mLoginView = checkNotNull(mLoginView);
        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {

    }


    @Override
    public void doLogin() {
        mLoginView.openMainActivity();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }
}
