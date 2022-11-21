package com.rsakin.sadapay.casestudy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Inventory {

    public Inventory(String inventoryFile) {
        buildInventory(inventoryFile);
    }

    private void buildInventory(String inventoryFile) {
        // To build the inventory, we need to parse all the lines to fill items and add to inventory item list
        try {
            final var allLines =
                    Files.readAllLines(Paths.get(".", inventoryFile));

//            allLines.forEach(System.out::println);
        } catch (IOException e) {
            System.err.println("There is no such file [ " + inventoryFile + " ]");
        }

    }

}
