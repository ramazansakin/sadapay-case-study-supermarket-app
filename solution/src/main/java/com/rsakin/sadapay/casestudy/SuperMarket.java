package com.rsakin.sadapay.casestudy;

public class SuperMarket {

    private static Inventory inventory;
    public static final String BASE_FOLDER = ".\\..\\functional_spec\\fixtures";
    // we can use List to multiply cashiers to serve multiple users simultaneously
    // But also we need to consider concurrency issues then
    private Cashier cashier;

    public SuperMarket(final String inventoryFile) {
        inventory = Inventory.getInventory(inventoryFile);
        cashier = new Cashier();
    }

    public void work() {
        // start cashier to serve user or process commandFile depends on the related argument
        cashier.work();
    }

    public static Inventory getInventory() {
        return inventory;
    }
}
