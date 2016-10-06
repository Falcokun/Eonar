package pe.scargglioni.eonar.backend.data;


public class Data {
    public static final String TYPE_DISTANCE = "distance";
    public static final String TYPE_DURATION_TRAFFIC = "duration_in_traffic";
    public static final String TYPE_TIME = "duration";

    public enum TYPE {
        TIME, DISTANCE, UNKNOWN
    }


    public String text;
    public String value;

    public Data() {
    }

    public TYPE getDataType() {
        switch (text) {
            case TYPE_DISTANCE:
                return TYPE.DISTANCE;
            case TYPE_DURATION_TRAFFIC:
                return TYPE.DISTANCE;
            case TYPE_TIME:
                return TYPE.TIME;
            default:
                return TYPE.UNKNOWN;
        }
    }

    @Override
    public String toString() {
        return "Data{" +
                "text='" + text + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}