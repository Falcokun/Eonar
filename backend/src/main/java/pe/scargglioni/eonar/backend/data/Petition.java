package pe.scargglioni.eonar.backend.data;


import java.util.Date;
import java.util.UUID;

/**
 * Petition
 */

public class Petition {
    private final String mId;

    public Double latitute;
    public Double longitude;

    public boolean isAttended;
    public Date alarmTime;
    public Date attentionTime;

    public Petition() {
        mId = UUID.randomUUID().toString();
    }

    public Petition(Double latitute, Double longitude) {
        mId = UUID.randomUUID().toString();
        this.latitute = latitute;
        this.longitude = longitude;
        isAttended = false;
    }
}
