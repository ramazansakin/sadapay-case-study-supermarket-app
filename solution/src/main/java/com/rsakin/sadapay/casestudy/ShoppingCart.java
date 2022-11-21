package com.rsakin.sadapay.casestudy;

import java.util.HashSet;
import java.util.Set;

public class ShoppingCart {

    private Set<Item> items;

    public ShoppingCart() {
        items = new HashSet<>();
    }

    public void addItem(final Item newItem) {
        boolean isAdded = items.add(newItem);
        // If the item not added, then that s been already added and need to be updated
        if (!isAdded) {
            Item addedItem = items.stream().filter(item -> item.getName().equals(newItem.getName())).findFirst().get();
            newItem.setQuantity(newItem.getQuantity() + addedItem.getQuantity());
            items.remove(addedItem);
            items.add(newItem);
        }
    }

    public void calculateBill() {
        // If we have no concern regarding concurrency here and not need to run orderly,
        // we can use paralelStream to make the performance better
        double subTotal = items.parallelStream().map(item -> item.getPrice() * item.getQuantity()).reduce(0.0, Double::sum);
        double discount = 0.0;
        double total = subTotal - discount;
        System.out.println("subtotal:" + subTotal + ", discount:" + discount + ", total:" + total);
    }

}
