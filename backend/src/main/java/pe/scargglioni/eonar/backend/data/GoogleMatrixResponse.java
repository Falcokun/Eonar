package pe.scargglioni.eonar.backend.data;


import java.util.List;

public class GoogleMatrixResponse {
    public String status;
    public List<String> destination_addresses;
    public List<String> origin_addresses;
    public List<ElementRow> rows;

    public GoogleMatrixResponse() {
    }

    @Override
    public String toString() {
        return "GoogleMatrixResponse{" +
                "status='" + status + '\'' +
                ", destination_addresses=" + destination_addresses +
                ", origin_addresses=" + origin_addresses +
                ", rows=" + rows +
                '}';
    }
}
