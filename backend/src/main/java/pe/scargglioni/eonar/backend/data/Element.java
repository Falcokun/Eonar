package pe.scargglioni.eonar.backend.data;


public class Element {

    public static final String STATUS_OK = "OK";
    public static final String STATUS_ZERO = "ZERO_RESULTS";
    public static final String NOT_FOUND = "NOT_FOUND";

    public String status;
    public Data distance;
    public Data duration;
    public Data duration_in_traffic;

    public Element() {
    }

    public boolean correctStatus() {
        switch (status) {
            case STATUS_OK:
                return true;
            case STATUS_ZERO:
                return false;
            case NOT_FOUND:
                return false;
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return "Element{" +
                "status='" + status + '\'' +
                ", distance=" + distance +
                ", duration=" + duration +
                ", duration_in_traffic=" + duration_in_traffic +
                '}';
    }
}