package pe.scargglioni.eonar.backend.data.source;


import java.util.List;

import pe.scargglioni.eonar.backend.data.Ambulance;


public interface AmbulanceDataSource {

    interface LoadAmbulancescCallback {

        void onAmbulancesLoaded(List<Ambulance> ambulances);

        void onDataNotAvailable();
    }

    interface LoadAmbulanceCallback {

        void onAmbulanceLoaded(Ambulance ambulance);

        void onDataNotAvailable();
    }

    void getAmbulances(LoadAmbulancescCallback callback);

    void addAmbulance(Ambulance ambulance);
}
