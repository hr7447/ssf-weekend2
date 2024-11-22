package vttp.batch5.ssf.weekend2.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Cart implements Serializable {
    private String name;
    private Map<String, Integer> items;

    // Default constructor required for Jackson
    public Cart() {
        this.items = new HashMap<>();
    }

    @JsonCreator
    public Cart(@JsonProperty("name") String name) {
        this.name = name;
        this.items = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getItems() {
        if (items == null) {
            items = new HashMap<>();
        }
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public int getItemCount() {
        return getItems().size();
    }
}