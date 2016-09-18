package pe.scargglioni.eonar.data;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.UUID;

/**
 * Petition
 */
@IgnoreExtraProperties
public class Petition {
    private final String mId;

    public Petition() {
        mId = UUID.randomUUID().toString();
    }
}
