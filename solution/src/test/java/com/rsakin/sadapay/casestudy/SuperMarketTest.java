package com.rsakin.sadapay.casestudy;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;

class SuperMarketTest extends BaseTest {

    // First we have inventory file and we need to read all the lines properly
    @Test
    void shouldGetInventoryFileAndPrintContent() {
        // supermarket init with inventory file
        SuperMarket superMarket = new SuperMarket(".\\..\\functional_spec\\fixtures\\inventory.csv");

    }

    // doCommand refactored and now private
//    @Test
//    void shouldRunCommandCheckout() {
//        SuperMarket superMarket = new SuperMarket(".\\..\\functional_spec\\fixtures\\inventory.csv");
//        superMarket.doCommand("checkout");
//        assertEquals("empty cart\r\n", outContent.toString());
//    }
//
//    // First we need to checkout to start shopping
//    @Test
//    void shouldNotAddItemToCart_NeedToCheckoutFirst() {
//        // we need to create a loop to get commands and try to get checkout first to start shopping
//        SuperMarket superMarket = new SuperMarket(".\\..\\functional_spec\\fixtures\\inventory.csv");
//        superMarket.doCommand("add soap 5");
//        assertEquals("You need to 'checkout' first to start shopping!\r\n", errContent.toString());
//    }



}