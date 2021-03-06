package pe.scargglioni.eonar.ambulances;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

import pe.scargglioni.eonar.data.Ambulance;
import pe.scargglioni.eonar.data.Petition;
import pe.scargglioni.eonar.databinding.AmbulancesFragBinding;

import static com.google.common.base.Preconditions.checkNotNull;

public class AmbulancesFragment extends Fragment implements AmbulancesContract.View {

    private AmbulancesContract.Presenter mPresenter;

    private AmbulancesViewModel mAmbulancesViewModel;


    public AmbulancesFragment() {
        //Constructor Vacio
    }

    public static AmbulancesFragment newInstance() {
        return new AmbulancesFragment();
    }

    @Override
    public void setPresenter(@NonNull AmbulancesContract.Presenter presenter) {
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
        AmbulancesFragBinding ambulancesFragBinding = AmbulancesFragBinding.inflate(inflater, container, false);
        ambulancesFragBinding.setActionHandler(mPresenter);
        ambulancesFragBinding.setAmbulances(mAmbulancesViewModel);


        setHasOptionsMenu(true);
        View root = ambulancesFragBinding.getRoot();



        return root;
    }

    public void setViewModel(AmbulancesViewModel mAmbulancesViewModel) {
        this.mAmbulancesViewModel = mAmbulancesViewModel;
    }

    @Override
    public void showAmbulances(List<Ambulance> ambulances) {
        mAmbulancesViewModel.setAmbulances(ambulances);
    }
}
