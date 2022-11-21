package com.rsakin.sadapay.casestudy;

public class SuperMarket {

    private Inventory inventory;
    // To handle client commands on a more specific class - SRP
    private Cashier cashier;

    public SuperMarket(String inventoryFile) {
        this.inventory = new Inventory(inventoryFile);
        this.cashier = new Cashier();
    }

    public void doCommand(String commandLine) {
        cashier.doCommand(commandLine);
    }
}
