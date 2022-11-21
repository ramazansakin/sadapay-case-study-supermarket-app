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
                    add(parts);
                    break;
                case BILL:
                    bill();
                    break;
                case OFFER:
                    offer(parts);
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

    private void add(final String[] commandLineParts) {
        try {
            Item itemByName = SuperMarket.getInventory().findItemByName(commandLineParts[1]);
            int requestedQuantity = Integer.parseInt(commandLineParts[2]);
            // create new item to add to shopping-cart
            Item newCartItem = new Item(itemByName.getName(), itemByName.getPrice(), requestedQuantity);
            cart.addItem(newCartItem);
            // decrease the inventory item quantity
            itemByName.setQuantity(itemByName.getQuantity() - requestedQuantity);
            System.out.println("added " + newCartItem.getName() + " " + requestedQuantity);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }
    }

    private void bill() {
        cart.calculateBill();
    }

    private void offer(final String[] commandLineParts) {
        String offerType = commandLineParts[1];
        String itemOffered = commandLineParts[2];
        addOffer(offerType, itemOffered);
    }

    private void addOffer(final String offerType, final String itemOffered) {
        // need to check item in cart
        Item itemByName = cart.findItemByName(itemOffered);
        try {
            if ("buy_2_get_1_free".equals(offerType)) {
                int offeredItemNumber = 0;
                if (itemByName.getQuantity() > 3) {
                    // can be defined a custom exp but I just prefer to return 0 not to impact discount
                    // System.err.println("There is/are not enough item to get this offer.");
                    offeredItemNumber = itemByName.getQuantity() / 3;
                }
                double discount = offeredItemNumber * itemByName.getPrice();
                cart.setDiscount(discount);
                System.out.println("offer added");
            }
        } catch (Exception ex) {
            System.err.println("There is no such item [ " + itemOffered + " ] in inventory");
        }
    }
}
