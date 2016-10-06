package pe.scargglioni.eonar.data.source;


import java.util.List;

import pe.scargglioni.eonar.data.Petition;

public interface PetitionDataSource {
    interface LoadPetitionscCallback {

        void onAmbulancesLoaded(List<Petition> petitions);

        void onDataNotAvailable();
    }

    interface LoadPetitionCallback {

        void onAmbulanceLoaded(Petition petition);

        void onDataNotAvailable();
    }

     void createPetition(Double latitude,  Double longitude);

     void updatePetition(Petition petition);
}
