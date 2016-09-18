package pe.scargglioni.eonar.data;

import java.util.UUID;

/**
 * Petition
 */
public class Petition {
    private final String mId;

    public Petition() {
        mId = UUID.randomUUID().toString();
    }
}
