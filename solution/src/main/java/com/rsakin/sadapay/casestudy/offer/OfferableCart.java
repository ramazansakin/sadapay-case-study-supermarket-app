package com.rsakin.sadapay.casestudy.offer;

import com.rsakin.sadapay.casestudy.Item;

import java.util.Set;

public interface OfferableCart {
    double calculateDiscount(final Set<Item> itemOffered);
}
