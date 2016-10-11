package pe.scargglioni.eonar.backend.servlet;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.scargglioni.eonar.backend.data.Ambulance;
import pe.scargglioni.eonar.backend.data.Element;
import pe.scargglioni.eonar.backend.data.ElementRow;
import pe.scargglioni.eonar.backend.data.GoogleMatrixResponse;
import pe.scargglioni.eonar.backend.data.Petition;
import pe.scargglioni.eonar.backend.data.source.remote.AmbulanceFirebaseDataSource;
import pe.scargglioni.eonar.backend.data.source.remote.GoogleMatrixResponseGoogleApiDataSource;
import pe.scargglioni.eonar.backend.data.source.remote.PetitionFirebaseDataSource;


public class RequestResponseServlet extends HttpServlet {
    private DatabaseReference firebase;
    private List<Ambulance> ambulanceList;
    private List<Petition> petitionList;

    private CountDownLatch latch;

    @Override
    public void init(ServletConfig config) {
        String credential = config.getInitParameter("credential");
        String databaseUrl = config.getInitParameter("databaseUrl");


        FirebaseOptions options = new FirebaseOptions.Builder()
                .setServiceAccount(config.getServletContext().getResourceAsStream(credential))
                .setDatabaseUrl(databaseUrl)
                .build();
        FirebaseApp.initializeApp(options);
        firebase = FirebaseDatabase.getInstance().getReference();


    }

    private void findAmbulances() {
        firebase.child("ambulances").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ambulance : dataSnapshot.getChildren()) {
                    ambulanceList.add(ambulance.getValue(Ambulance.class));
                    System.out.println(ambulance);
                }
                System.out.println(ambulanceList);
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
            }
        });
    }

    private void findPetitions() {
        firebase.child("petitions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot petition : dataSnapshot.getChildren()) {
                    petitionList.add(petition.getValue(Petition.class));
                    System.out.println(petition);
                }
                System.out.println(petitionList);
                latch.countDown();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getMessage());
            }
        });
    }


    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doPost(req, resp);
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse)
     * Just printing all user event logs stored in memory of this Servlet instance.
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        latch = new CountDownLatch(2);

        ambulanceList = new ArrayList<>();
        petitionList = new ArrayList<>();
        System.out.println("Ambulances");
        findAmbulances();
        System.out.println("Petitions");
        findPetitions();

        resp.setContentType("text/plain");
        PrintWriter writer = resp.getWriter();
        writer.print("Terminado");

        try {
            latch.await();
            matchReferences();
        } catch (InterruptedException e) {
            writer.print("Bug");
            e.printStackTrace();
        }
    }

    private void matchReferences() {
        System.out.println("Matching");
        //Example: https://maps.googleapis.com/maps/api/distancematrix/json?origins=Seattle&destinations=San+Francisco&key=AIzaSyB7p7S1f2KQQco3xlGVM_JY-mCcnollOR0
        String origin = "";
        String destiny = "";
        String request = "";
        for (Ambulance ambulance : ambulanceList) {
            origin += ambulance.latitute + "," + ambulance.longitude + "%7C";
        }
        for (Petition petition : petitionList) {
            destiny = petition.latitute + "," + petition.longitude;
            String traffic_mode = "optimistic";
            request = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + origin + "&destinations=" + destiny + "&traffic_model=" + traffic_mode + "&departure_time=now&key=AIzaSyB7p7S1f2KQQco3xlGVM_JY-mCcnollOR0";
            System.out.println(request);
            GoogleMatrixResponse response = new GoogleMatrixResponse();
            try {
                response = GoogleMatrixResponseGoogleApiDataSource.getINSTANCE().getResponsefromHTTP(request);
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }

            petition.isAttended = false;
            petition.travelTime = 99999;
            for (ElementRow row : response.rows) {
                if (Objects.equals(row.elements.get(0).status, Element.STATUS_OK)) {
                    if (petition.travelTime > Double.valueOf(row.elements.get(0).duration.value)) {
                        petition.travelTime = Double.valueOf(row.elements.get(0).duration.value);
                        petition.isAttended = true;
                        petition.ambulance = ambulanceList.get(response.rows.indexOf(row));
                    }
                    PetitionFirebaseDataSource.getInstance().updatePetition(petition);
                }
            }
        }
    }

}
