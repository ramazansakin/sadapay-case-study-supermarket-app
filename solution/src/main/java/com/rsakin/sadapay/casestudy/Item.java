package com.rsakin.sadapay.casestudy;

import com.rsakin.sadapay.casestudy.offer.OfferableItem;
import com.rsakin.sadapay.casestudy.offer.ZeroOffer;

import java.util.Objects;

public class Item implements Comparable<Item> {

    private final String name;
    private final double price;
    private int quantity;
    // as default, there is no discount
    private OfferableItem offer = new ZeroOffer();

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return getName().equals(item.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public OfferableItem getOffer() {
        return offer;
    }

    public void setOffer(OfferableItem offer) {
        this.offer = offer;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public String fileFormat() {
        return name + "," + price + "," + quantity;
    }

    public double calculateDiscount() {
        // we can define an anonymous lambda function to define default offer not to impact subtotal
        // return getOffer().orElse(itemOffered -> 0).calculateDiscount(this);
        // Or we can explicitly define an DefaultOffer class to cover non-offered items properly
        return getOffer().calculateDiscount(this);
    }

    @Override
    public int compareTo(Item o) {
        if (this.price > o.getPrice())
            return 1;
        return -1;
    }
}