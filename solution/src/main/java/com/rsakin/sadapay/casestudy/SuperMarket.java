package com.rsakin.sadapay.casestudy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SuperMarket {

    // SuperMarket needs to be Singleton
    private static SuperMarket superMarket;
    private static Inventory inventory;
    public static final String BASE_FOLDER = ".\\..\\functional_spec\\fixtures";
    // we can use List to multiply cashiers to serve multiple users simultaneously
    // But also we need to consider concurrency issues then
    private Cashier cashier;

    private SuperMarket(final String inventoryFile) {
        inventory = Inventory.getInventory(inventoryFile);
        cashier = new Cashier();
    }

    public static SuperMarket getSuperMarket(final String inventoryFile) {
        if (superMarket == null) {
            superMarket = new SuperMarket(inventoryFile);
        }
        return superMarket;
    }

    public void work(final String commandsFile) {
        // start cashier to serve user or process commandFile depends on the related argument
        InputStream inputStream = System.in;
        if (commandsFile != null) {
            try {
                File file = new File(BASE_FOLDER + "\\" + commandsFile);
                inputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                System.err.println("Commands file not found [ " + commandsFile + " ]");
                return;
            }
        }
        cashier.work(inputStream);
    }

    public static Inventory getInventory() {
        return inventory;
    }
}
