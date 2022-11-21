package com.rsakin.sadapay.casestudy;

public class SuperMarket {

    private Inventory inventory;

    public SuperMarket(String inventoryFile) {
        this.inventory = new Inventory(inventoryFile);
    }

    public void doCommand(String commandLine) {
        if(commandLine.equals("checkout")){
            System.out.println("empty cart");
        }
    }
}
