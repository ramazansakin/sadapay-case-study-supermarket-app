package com.rsakin.sadapay.casestudy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public class Inventory {

    // Inventory needs to be singleton
    private static Inventory inventory = null;
    private static final String INVENTORY_ITEM_PROPERTY_REGEX = ",";
    private static Set<Item> items;
    private static String inventoryListFile;

    private Inventory() {
        items = new HashSet<>();
    }

    public static Inventory getInventory(final String inventoryFile) {
        // Lazy Loading
        if (inventory == null) {
            inventoryListFile = inventoryFile;
            inventory = new Inventory();
            buildInventory(inventoryListFile);
        }
        return inventory;
    }

    private static void buildInventory(final String inventoryFileName) {
        // To build the inventory, we need to parse all the lines to fill items and add to inventory item list
        try {
            final var allLines =
                    Files.readAllLines(Paths.get(SuperMarket.BASE_FOLDER, inventoryFileName));

            allLines.forEach(line -> {
                String[] parts = line.split(INVENTORY_ITEM_PROPERTY_REGEX);
                Item item = new Item(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]));
                items.add(item);
            });

            printInventoryStatus();

        } catch (IOException e) {
            System.err.println("There is no such file [ " + inventoryFileName + " ]");
        }
    }

    public static void printInventoryStatus() {
        StringJoiner inventoryListFormat = new StringJoiner("\n");
        inventoryListFormat.add("--- Inventory Item List ---");
        items.forEach(item -> inventoryListFormat.add(item.toString()));
        inventoryListFormat.add("--- End of List ---");
        System.out.println(inventoryListFormat);
    }

}
