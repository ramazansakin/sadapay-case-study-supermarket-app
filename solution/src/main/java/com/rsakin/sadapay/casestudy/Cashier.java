package com.rsakin.sadapay.casestudy;

public class Cashier {

    private ShoppingCart cart;

    public void doCommand(String commandLine) {
        if (!commandLine.equals("checkout")) {
            System.err.println("You need to 'checkout' first to start shopping!");
            return;
        }
        System.out.println("empty cart");
    }

    public void add(String commandLine) {
        // parse the commandLine
        String[] parts = commandLine.split(" ");
        System.out.println("added " + parts[1] + " " + parts[2]);
    }
}
