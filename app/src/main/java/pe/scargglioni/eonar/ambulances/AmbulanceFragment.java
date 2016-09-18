package pe.scargglioni.eonar.ambulances;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.scargglioni.eonar.data.Ambulance;
import pe.scargglioni.eonar.databinding.AmbulancesFragBinding;
import pe.scargglioni.eonar.login.LoginContract;

public class AmbulanceFragment extends Fragment implements AmbulanceContract.View {

    private AmbulanceContract.Presenter mPresenter;

    private AmbulanceViewModel mAmbulanceViewModel;


    public AmbulanceFragment() {

    }

    public static AmbulanceFragment newInstance() {
        return new AmbulanceFragment();
    }


    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull AmbulanceContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AmbulancesFragBinding ambulancesFragBinding = AmbulancesFragBinding.inflate(inflater, container, false);
        ambulancesFragBinding.setActionHandler(mPresenter);
        setHasOptionsMenu(true);
        View root = ambulancesFragBinding.getRoot();
        return root;
    }

    public void setViewModel(AmbulanceViewModel mAmbulanceViewModel) {
        this.mAmbulanceViewModel = mAmbulanceViewModel;

    }
}
