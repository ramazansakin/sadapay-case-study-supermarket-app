package com.rsakin.sadapay.casestudy.offer;


import com.rsakin.sadapay.casestudy.Item;

public class BuyTwoGetOneFree implements Offerable {

    private static final int MIN_NUMBER_OFFERABLE = 3;

    @Override
    public double calculateDiscount(Item itemOffered) {
        int discountedItemNumber = makeOffer(itemOffered, MIN_NUMBER_OFFERABLE);
        return discountedItemNumber * itemOffered.getPrice();
    }
}
