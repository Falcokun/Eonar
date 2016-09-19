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

    public Ambulance(String license, String owner) {
        mId = UUID.randomUUID().toString();
        this.license = license;
        this.owner = owner;
    }

    public void setPosition(Double latitute, Double longitude) {
        this.latitute = latitute;
        this.longitude = longitude;
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
