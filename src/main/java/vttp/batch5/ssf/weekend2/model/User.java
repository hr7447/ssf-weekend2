package vttp.batch5.ssf.weekend2.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements Serializable {
    private String userId;
    private Map<String, Cart> carts;

    // Default constructor required for Jackson
    public User() {
        this.carts = new HashMap<>();
    }

    @JsonCreator
    public User(@JsonProperty("userId") String userId) {
        this.userId = userId;
        this.carts = new HashMap<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Cart> getCarts() {
        if (carts == null) {
            carts = new HashMap<>();
        }
        return carts;
    }

    public void setCarts(Map<String, Cart> carts) {
        this.carts = carts;
    }
}