package com.rsakin.sadapay.casestudy;

import org.junit.jupiter.api.Test;

class SuperMarketTest {

    // First we have inventory file and we need to read all the lines properly
    @Test
    void shouldGetInventoryFileAndPrintContent() {
        // supermarket init with inventory file
        SuperMarket superMarket = new SuperMarket(".\\..\\functional_spec\\fixtures\\inventory.csv");

    }

}