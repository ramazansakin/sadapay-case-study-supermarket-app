package com.rsakin.sadapay.casestudy.offer;

import com.rsakin.sadapay.casestudy.Item;

import java.util.Set;

public class BuyFourGetCheapest implements OfferableCart {

    @Override
    public double calculateDiscount(Set<Item> itemOfferedList) {
        Item item = itemOfferedList.stream().sorted().findFirst().get();
        double amount = itemOfferedList.size() * item.getPrice();
        double subTotal = itemOfferedList.stream().mapToDouble(Item::getPrice).reduce(0, Double::sum);
        return subTotal - amount;
    }
}
