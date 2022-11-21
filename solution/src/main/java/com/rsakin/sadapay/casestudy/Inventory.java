package com.rsakin.sadapay.casestudy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Inventory {

    public Inventory(String inventoryFile) throws IOException {
        buildInventory(inventoryFile);
    }

    private void buildInventory(String inventoryFile) throws IOException {
        // To build the inventory, we need to parse all the lines to fill items and add to inventory item list
        final var allLines =
                Files.readAllLines(Paths.get(".", inventoryFile));

        allLines.forEach(System.out::println);

    }

}
