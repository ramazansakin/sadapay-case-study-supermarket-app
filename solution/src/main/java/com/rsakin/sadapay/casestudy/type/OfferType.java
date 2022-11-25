package com.rsakin.sadapay.casestudy.type;

public enum OfferType {
    BUY_2_GET_1_FREE("buy_2_get_1_free"),
    BUY_1_GET_HALF_OFF("buy_1_get_half_off"),
    BUY_4_GET_CHEAPEST("buy_4_get_cheapest");

    private String type;

    public String getType() {
        return type;
    }

    OfferType(String type) {
        this.type = type;
    }
}
