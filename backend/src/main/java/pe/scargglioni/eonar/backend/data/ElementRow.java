package pe.scargglioni.eonar.backend.data;

import java.util.List;



public class ElementRow {
    public List<Element> elements;

    public ElementRow() {
    }

    @Override
    public String toString() {
        return "ElementRow{" +
                "elements=" + elements +
                '}';
    }
}
