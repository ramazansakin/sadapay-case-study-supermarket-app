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
        String command;
        do {
            command = in.nextLine();
        } while (doCommand(command));

    }

    private boolean doCommand(final String commandLine) {
        // parse the commandLine
        String[] parts = commandLine.split(" ");
        try {
            CommandType commandType = CommandType.valueOf(parts[0].toUpperCase(Locale.ROOT));
            switch (commandType) {
                case CHECKOUT:
                    return checkout();
                case ADD:
                    add(parts);
                    break;
                case REMOVE:
                    remove(parts);
                    break;
                case OFFER:
                    offer(parts);
                    break;
                case BILL:
                    bill();
                    break;
                case INVENTORY_STATUS:
                    inventoryStatus();
                    break;
            }
        } catch (IllegalArgumentException exp) {
            System.err.println("There is no such command [ " + parts[0] + " ]");
        }
        return true;
    }

    private void inventoryStatus() {
        Inventory.printInventoryStatus();
    }

    private boolean checkout() {
        // There could be more than one cashiers
        // So cart is locally singleton to be make sure that there is one cart per cashier/user
        // Also it checks whether the user started shopping or done
        if (cart == null) {
            System.out.println("empty cart");
            this.cart = new ShoppingCart();
            return true;
        }
        System.out.println("done");
        // update the inventory file before exiting the app
        SuperMarket.getInventory().updateFile();
        return false;
    }

    private boolean isCartInitialized() {
        if (cart == null) {
            System.err.println("You need to 'checkout' first to start shopping!");
            return false;
        }
        return true;
    }

    private void add(final String[] commandLineParts) {
        if (!isCartInitialized()) return;
        try {
            Item itemByName = SuperMarket.getInventory().findItemByName(commandLineParts[1]);
            int requestedQuantity = getRequestedQuantity(itemByName, commandLineParts[2]);
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

    private void remove(final String[] commandLineParts) {
        if (!isCartInitialized()) return;
        try {
            Item itemByName = cart.findItemByName(commandLineParts[1]);
            int requestedQuantity = getRequestedQuantity(itemByName, commandLineParts[2]);
            // update the item via decreasing the quantity
            itemByName.setQuantity(itemByName.getQuantity() - requestedQuantity);
            // update the inventory to add removed items
            Item invItem = SuperMarket.getInventory().findItemByName(itemByName.getName());
            invItem.setQuantity(invItem.getQuantity() + requestedQuantity);
            System.out.println("removed " + itemByName.getName() + " " + requestedQuantity);
        } catch (Exception ex) {
            System.err.println("There is no such item [ " + commandLineParts[1] + " ] in inventory");
        }
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
        if (!isCartInitialized()) return;
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

    private void bill() {
        if (!isCartInitialized()) return;
        cart.calculateBill();
    }

}