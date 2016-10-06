package pe.scargglioni.eonar.backend.data.source.remote;


import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import pe.scargglioni.eonar.backend.data.GoogleMatrixResponse;
import pe.scargglioni.eonar.backend.data.source.GoogleMatrixResponseDataSource;

public class GoogleMatrixResponseGoogleApiDataSource implements GoogleMatrixResponseDataSource {
    private Gson gson;

    private static GoogleMatrixResponseGoogleApiDataSource INSTANCE;

    private GoogleMatrixResponseGoogleApiDataSource() {
        gson = new Gson();
    }

    public static GoogleMatrixResponseGoogleApiDataSource getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new GoogleMatrixResponseGoogleApiDataSource();
        }
        return INSTANCE;
    }

    public GoogleMatrixResponse getResponsefromHTTP(String url) throws IOException {
        return gson.fromJson(new InputStreamReader(new URL(url).openStream()), GoogleMatrixResponse.class);
    }

}
