package com.rsakin.sadapay.casestudy;

import java.util.Locale;
import java.util.Scanner;

public class Cashier {

    private ShoppingCart cart;

    public void work() {
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!"checkout".equals(command)) {
            System.err.println("You need to 'checkout' first to start shopping!");
            command = in.nextLine();
        }
        // checkout to start shopping
        doCommand(command);
        command = in.nextLine();
        while (!"checkout".equals(command)) {
            doCommand(command);
            command = in.nextLine();
        }
        // checkout to stop shopping
        doCommand(command);
    }

    private void doCommand(final String commandLine) {
        // parse the commandLine
        String[] parts = commandLine.split(" ");
        try {
            CommandType commandType = CommandType.valueOf(parts[0].toUpperCase(Locale.ROOT));
            switch (commandType) {
                case CHECKOUT:
                    checkout();
                    break;
                case ADD:
                    add(commandLine);
                    break;
            }
        } catch (IllegalArgumentException exp) {
            System.err.println("There is no such command [ " + parts[0] + " ]");
        }

    }

    private void checkout() {
        // There could be more than one cashiers
        // So cart is locally singleton to be make sure that there is one cart per cashier/user
        // Also it checks whether the user started shopping or done
        if (cart == null) {
            System.out.println("empty cart");
            this.cart = new ShoppingCart();
            return;
        }
        System.out.println("done");
    }

    public void add(String commandLine) {
        // parse the commandLine
        String[] parts = commandLine.split(" ");
        System.out.println("added " + parts[1] + " " + parts[2]);
    }
}
