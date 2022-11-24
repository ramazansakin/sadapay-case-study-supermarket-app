package com.rsakin.sadapay.casestudy;

public class SuperMarketApp {

    public static void main(String[] args) {
        // Initialize supermarket with an inventory file
        SuperMarket superMarket = SuperMarket.getSuperMarket("inventory.csv");
        // Start to work the super-market to serve
        superMarket.work(args.length == 2 ? args[1] : null);
    }
}
