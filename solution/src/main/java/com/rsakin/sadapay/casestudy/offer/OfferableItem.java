package com.rsakin.sadapay.casestudy.offer;


import com.rsakin.sadapay.casestudy.Item;

// can be defined as @FunctionalInterface explicitly or directly can be used
// but I did not use this class on any Lambda operation
public interface OfferableItem {

    // can be defined as static if we think this is always the same impl for all subclasses
    // but I prefer default to make it overridable
    default int makeOffer(Item itemOffered, final int minNumberToOffer) {
        // need to check if there is/are at least minNumberToOffer item/s to validate offer
        if (itemOffered.getQuantity() < minNumberToOffer) {
            // can be defined a custom exp but I just prefer to return 0 not to impact discount
            System.err.println("There is/are not enough item to get this offer.");
            return 0;
        }
        return itemOffered.getQuantity() / minNumberToOffer;
    }

    double calculateDiscount(final Item itemOffered);

}
