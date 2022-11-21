package com.rsakin.sadapay.casestudy.offer;


import com.rsakin.sadapay.casestudy.Item;

public class BuyOneGetHalfOff implements Offerable {

    static final int MIN_NUMBER_OFFERABLE = 2;

    @Override
    public double calculateDiscount(final Item itemOffered) {
        int discountedItemNumber = makeOffer(itemOffered, MIN_NUMBER_OFFERABLE);
        return discountedItemNumber * (itemOffered.getPrice() / 2.0);
    }
}
