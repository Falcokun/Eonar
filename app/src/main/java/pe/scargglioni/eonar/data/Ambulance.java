package pe.scargglioni.eonar.data;

import java.util.UUID;

/**
 * Ambulance Data
 */
public class Ambulance {
    private final String mId;

    public Ambulance() {
        mId = UUID.randomUUID().toString();
    }
}
