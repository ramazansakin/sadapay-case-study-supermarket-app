package com.rsakin.sadapay.casestudy;

import java.io.IOException;

public class SuperMarketApp {

    public static void main(String[] args) throws IOException {
        SuperMarket superMarket = new SuperMarket(".\\..\\functional_spec\\fixtures\\inventory.csv");
    }
}
