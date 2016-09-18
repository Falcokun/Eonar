package pe.scargglioni.eonar.data;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.UUID;

/**
 * Ambulance Data
 */
@IgnoreExtraProperties
public class Ambulance {
    private final String mId;
    public String license;
    public String owner;

    public Double latitute;
    public Double longitude;

    public Ambulance() {
        mId = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Ambulance{" +
                "mId='" + mId + '\'' +
                ", license='" + license + '\'' +
                ", owner='" + owner + '\'' +
                '}';
    }
}
