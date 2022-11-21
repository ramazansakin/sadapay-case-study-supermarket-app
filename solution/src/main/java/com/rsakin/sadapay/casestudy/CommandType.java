package com.rsakin.sadapay.casestudy;

public enum CommandType {
    CHECKOUT("checkout"),
    ADD("add"),
    BILL("bill");

    private String type;

    CommandType(String type) {
        this.type = type;
    }
}

