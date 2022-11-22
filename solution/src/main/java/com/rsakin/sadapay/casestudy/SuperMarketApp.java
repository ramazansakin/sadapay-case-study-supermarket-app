package com.rsakin.sadapay.casestudy;

public class SuperMarketApp {

    public static void main(String[] args) {
        // Start to work the super-market to serve
        SuperMarket superMarket = new SuperMarket(args[0]);
        superMarket.work(args.length == 2 ? args[1] : null);
    }
}
