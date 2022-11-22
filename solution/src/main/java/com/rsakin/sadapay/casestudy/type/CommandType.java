package com.rsakin.sadapay.casestudy.type;

public enum CommandType {
    CHECKOUT("checkout"),
    ADD("add"),
    REMOVE("remove"),
    OFFER("offer"),
    BILL("bill"),
    INVENTORY_STATUS("inventory_status");

    private String type;

    CommandType(String type) {
        this.type = type;
    }
}


