package pe.scargglioni.eonar.backend.data.source.remote;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import pe.scargglioni.eonar.backend.data.Petition;
import pe.scargglioni.eonar.backend.data.source.PetitionDataSource;

public class PetitionFirebaseDataSource implements PetitionDataSource {

    private static PetitionFirebaseDataSource INSTANCE;
    DatabaseReference petitionReference;

    public static PetitionFirebaseDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PetitionFirebaseDataSource();
        }
        return INSTANCE;
    }

    ;

    private PetitionFirebaseDataSource() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        petitionReference = database.getReference("petitions");
    }


    @Override
    public void createPetition(Double latitude, Double longitude) {
        Petition newPetition = new Petition(latitude, longitude);
        String key = petitionReference.push().getKey();
        petitionReference.child(key).setValue(newPetition);
    }
}