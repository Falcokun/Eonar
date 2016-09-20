package pe.scargglioni.eonar.backend.data.source;


import java.util.List;

import pe.scargglioni.eonar.backend.data.Petition;


public interface PetitionDataSource {
    interface LoadPetitionscCallback {

        void onAmbulancesLoaded(List<Petition> petitions);

        void onDataNotAvailable();
    }

    interface LoadPetitionCallback {

        void onAmbulanceLoaded(Petition petition);

        void onDataNotAvailable();
    }

    public void createPetition(Double latitude, Double longitude);
}
