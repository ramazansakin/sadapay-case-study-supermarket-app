package com.rsakin.sadapay.casestudy;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;

class SuperMarketTest extends BaseTest {

    SuperMarket superMarket = new SuperMarket("inventory.csv");

    // First we have inventory file and we need to read all the lines properly
    @Test
    void shouldGetInventoryFileAndPrintContent() {
        // supermarket init with inventory file
        provideInput("checkout\r\ncheckout");
        superMarket.work();
    }

    // After refactor, Now we need to see 'empty cart' and 'done' outputs after two checkouts,
    @Test
    void shouldRunCommandCheckout() {
        provideInput("checkout\r\ncheckout");
        superMarket.work();
        assertEquals("empty cart\r\ndone\r\n", outContent.toString());
    }

    // First we need to checkout to start shopping
    @Test
    void shouldNotAddItemToCart_NeedToCheckoutFirst() {
        // we need to create a loop to get commands and try to get checkout first to start shopping
        provideInput("add soap 5\r\ncheckout\r\ncheckout");
        superMarket.work();
        // write add to see the below error message
        assertEquals("You need to 'checkout' first to start shopping!\r\n", errContent.toString());
    }

    // need to see bill details when I run 'bill' command
    @Test
    void shouldRunOfferCommandTodefineOffer_BUY_2_GET_1_FREE() {
        provideInput("checkout\r\nadd soap 5\r\noffer buy_2_get_1_free soap\r\nbill\r\ncheckout");
        superMarket.work();
        assertEquals("empty cart\r\nadded soap 5\r\nsubtotal:50.0, discount:0.0, total:50.0\r\noffer added\r\ndone\r\n", outContent.toString());
    }

}