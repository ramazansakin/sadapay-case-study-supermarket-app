package com.rsakin.sadapay.casestudy;

import java.io.IOException;

public class SuperMarket {

    private Inventory inventory;

    public SuperMarket(String inventoryFile) throws IOException {
        this.inventory = new Inventory(inventoryFile);
    }

}
