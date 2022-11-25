package com.rsakin.sadapay.casestudy.offer;


import com.rsakin.sadapay.casestudy.Item;

public class ZeroOffer implements OfferableItem {

    @Override
    public double calculateDiscount(Item itemOffered) {
        // no impact on subtotal, just to cover NPE cases
        return 0;
    }

}
