package pe.scargglioni.eonar.backend.data.source.remote;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import pe.scargglioni.eonar.backend.data.Ambulance;
import pe.scargglioni.eonar.backend.data.source.AmbulanceDataSource;

public class AmbulanceFirebaseDataSource implements AmbulanceDataSource {
    private static AmbulanceFirebaseDataSource INSTANCE;
    DatabaseReference ambulanceReference;

    public static AmbulanceFirebaseDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AmbulanceFirebaseDataSource();
        }
        return INSTANCE;
    }

    ;

    private AmbulanceFirebaseDataSource() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        ambulanceReference = database.getReference("ambulances");
    }

    @Override
    public void getAmbulances(final LoadAmbulancescCallback callback) {
        ambulanceReference.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Ambulance> ambulances = new ArrayList<>();
                for (DataSnapshot ambulanceSnapshot : dataSnapshot.getChildren()) {
                    ambulances.add(ambulanceSnapshot.getValue(Ambulance.class));
                }
                callback.onAmbulancesLoaded(ambulances);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void addAmbulance(Ambulance ambulance) {
        String key = ambulanceReference.push().getKey();
        ambulanceReference.child(key).setValue(ambulance);
    }

}
