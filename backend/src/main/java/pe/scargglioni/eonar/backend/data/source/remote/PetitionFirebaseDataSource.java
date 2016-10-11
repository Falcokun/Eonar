package pe.scargglioni.eonar.backend.data.source.remote;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import pe.scargglioni.eonar.backend.data.Petition;
import pe.scargglioni.eonar.backend.data.source.PetitionDataSource;

public class PetitionFirebaseDataSource implements PetitionDataSource {

    private static PetitionFirebaseDataSource INSTANCE;
    private DatabaseReference petitionReference;

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

    @Override
    public void updatePetition(final Petition petition) {
        final Query query = petitionReference.orderByChild("mId").equalTo(petition.mId).limitToFirst(1);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                petitionReference.child(dataSnapshot.getKey()).setValue(petition);
                query.removeEventListener(this);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
