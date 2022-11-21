package com.rsakin.sadapay.casestudy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SuperMarket {

    private String inventoryFile;

    public SuperMarket(String inventoryFile) {
        this.inventoryFile = inventoryFile;
    }

    public void buildInventory() {

        // To build the inventory, we need to parse all the lines to fill items and add to inventory item list
        try {
            final var allLines =
                    Files.readAllLines(Paths.get(".", inventoryFile));

            allLines.forEach(System.out::println);

        } catch (IOException e) {
            System.out.println("There is no such file [ " + inventoryFile + " ]");
        }

    }
}
