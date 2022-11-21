package com.rsakin.sadapay.casestudy;

import java.util.Scanner;

public class Cashier {

    private ShoppingCart cart;

    public void doCommand(String commandLine) {

        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!"checkout".equals(command)) {
            System.err.println("You need to 'checkout' first to start shopping!");
            command = in.nextLine();
        }
        // checkout to start shopping
        System.out.println("empty cart");
        command = in.nextLine();
        while (!"checkout".equals(command)) {
            doCommand(command);
            command = in.nextLine();
        }
        // checkout to stop shopping
        System.out.println("done");

    }

    public void add(String commandLine) {
        // parse the commandLine
        String[] parts = commandLine.split(" ");
        System.out.println("added " + parts[1] + " " + parts[2]);
    }
}
