package com.rsakin.sadapay.casestudy;

import com.rsakin.sadapay.casestudy.offer.BuyOneGetHalfOff;
import com.rsakin.sadapay.casestudy.offer.BuyTwoGetOneFree;
import com.rsakin.sadapay.casestudy.type.CommandType;
import com.rsakin.sadapay.casestudy.type.OfferType;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

public class Cashier {

    private ShoppingCart cart;

    public void work(final InputStream inputStream) {
        // Using Scanner for Getting Input from User
        Scanner in = new Scanner(inputStream);
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

    private int getRequestedQuantity(final Item itemByName, final String commandLinePart) {
        int requestedQuantity = Integer.parseInt(commandLinePart);
        if (requestedQuantity < 1) {
            throw new RuntimeException("Requested quantity needs to be at least more than 1"); // can be customized
        }
        if (itemByName.getQuantity() < requestedQuantity) {
            throw new RuntimeException("There is/are not enough item/s for [ "
                    + itemByName.getName() + " ]. [Remained : " + itemByName.getQuantity() + "]"); // can be customized
        }
        return requestedQuantity;
    }

    private void offer(final String[] commandLineParts) {
        String offerType = commandLineParts[1];
        String itemOffered = commandLineParts[2];
        addOffer(offerType, itemOffered);
    }

    private void addOffer(final String offerType, final String itemOffered) {
        try {
            OfferType offer = OfferType.valueOf(offerType.toUpperCase(Locale.ROOT));
            // need to check item in cart
            Item itemByName = cart.findItemByName(itemOffered);
            switch (offer) {
                case BUY_2_GET_1_FREE:
                    itemByName.setOffer(new BuyTwoGetOneFree());
                    break;
                case BUY_1_GET_HALF_OFF:
                    itemByName.setOffer(new BuyOneGetHalfOff());
                    break;
                default:
                    System.err.println("There is no such offer type [ " + offerType + " ]");
            }
            System.out.println("offer added");
        } catch (Exception ex) {
            System.err.println("There is no such item [ " + itemOffered + " ] in inventory");
        }
    }
}
