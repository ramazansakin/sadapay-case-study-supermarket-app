package com.rsakin.sadapay.casestudy;

public class SuperMarket {

    private Inventory inventory;

    public SuperMarket(String inventoryFile) {
        this.inventory = new Inventory(inventoryFile);
    }

}
