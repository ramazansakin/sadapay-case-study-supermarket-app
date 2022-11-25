package com.rsakin.sadapay.casestudy.offer;

import com.rsakin.sadapay.casestudy.Item;

import java.util.Set;

public class BuyFourGetCheapest implements OfferableCart {

    @Override
    public double calculateDiscount(Set<Item> itemOfferedList) {
        if (itemOfferedList.size() != 4) {
            System.err.println("The offer can not be applied to cart. You need to buy just 4 items");
        }
        Item item = itemOfferedList.stream().sorted().findFirst().get();
        double amount = itemOfferedList.size() * item.getPrice();
        double subTotal = itemOfferedList.stream().mapToDouble(Item::getPrice).reduce(0, Double::sum);
        return subTotal - amount;
    }
}
