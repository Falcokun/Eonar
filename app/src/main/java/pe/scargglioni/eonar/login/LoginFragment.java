package pe.scargglioni.eonar.login;

import static com.google.common.base.Preconditions.checkNotNull;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import pe.scargglioni.eonar.databinding.LoginFragBinding;

public class LoginFragment extends Fragment implements LoginContract.View {

    private LoginContract.Presenter mPresenter;

    private LoginViewModel mloginViewModel;

    public LoginFragment() {
        //Constructor Vacio
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void setPresenter(@NonNull LoginContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LoginFragBinding loginFragBinding = LoginFragBinding.inflate(inflater, container, false);
        loginFragBinding.setActionHandler(mPresenter);

        setHasOptionsMenu(true);
        View root = loginFragBinding.getRoot();
        return root;
    }

    public void setViewModel(LoginViewModel viewModel) {
        mloginViewModel = viewModel;
    }

}
