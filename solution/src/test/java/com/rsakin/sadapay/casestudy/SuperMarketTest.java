package com.rsakin.sadapay.casestudy;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;

class SuperMarketTest extends BaseTest {

    SuperMarket superMarket = new SuperMarket("inventory.csv");

    // First we have inventory file and we need to read all the lines properly
    @Test
    void shouldGetInventoryFileAndPrintContent() {
        // supermarket init with inventory file
        provideInput("checkout\ncheckout");
        superMarket.work();
    }

    // After refactor, Now we need to see 'empty cart' and 'done' outputs after two checkouts,
    @Test
    void shouldRunCommandCheckout() {
        provideInput("checkout\ncheckout");
        superMarket.work();
        assertEquals("empty cart\r\ndone\r\n", outContent.toString());
    }

    // First we need to checkout to start shopping
    @Test
    void shouldNotAddItemToCart_NeedToCheckoutFirst() {
        // we need to create a loop to get commands and try to get checkout first to start shopping
        provideInput("add soap 5\ncheckout\ncheckout");
        superMarket.work();
        // write add to see the below error message
        assertEquals("You need to 'checkout' first to start shopping!\r\n", errContent.toString());
    }

    // need to see bill details when I run 'bill' command
    @Test
    void shouldRunOfferCommandTodefineOffer_BUY_2_GET_1_FREE() {
        provideInput("checkout\nadd soap 5\noffer buy_2_get_1_free soap\nbill\ncheckout");
        superMarket.work();
        assertEquals("empty cart\r\nadded soap 5\r\noffer added\r\nsubtotal:50.0, discount:10.0, total:40.0\r\ndone\r\n", outContent.toString());
    }

    // need to see bill details when I run 'bill' command
    @Test
    void shouldRunOfferCommandTodefineOffer_BUY_1_GET_HALF_OFF() {
        provideInput("checkout\nadd soap 4\noffer buy_1_get_half_off soap\nbill\ncheckout");
        superMarket.work();
        assertEquals("empty cart\r\nadded soap 4\r\noffer added\r\nsubtotal:40.0, discount:10.0, total:30.0\r\ndone\r\n", outContent.toString());
    }

    // need to see the whole test flow in doc can run properly
    @Test
    void shouldWorkAllTheSampleCasesRunningProperly() {
        provideInput("checkout\nadd soap 5\nadd bread 1\nbill\noffer buy_2_get_1_free soap\nbill\nadd soap 1\nbill\noffer buy_1_get_half_off bread\nadd bread 1\nbill\ncheckout");
        superMarket.work();
        assertEquals("empty cart\r\n" +
                "added soap 5\r\n" +
                "added bread 1\r\n" +
                "subtotal:52.5, discount:0.0, total:52.5\r\n" +
                "offer added\r\n" +
                "subtotal:52.5, discount:10.0, total:42.5\r\n" +
                "added soap 1\r\n" +
                "subtotal:62.5, discount:20.0, total:42.5\r\n" +
                "offer added\r\n" +
                "added bread 1\r\n" +
                "subtotal:65.0, discount:21.25, total:43.75\r\n" +
                "done\r\n", outContent.toString());
    }

    @Test
    void shouldWorkWithCommandFile() {
        superMarket.work("commands.txt");
        assertEquals("empty cart\r\n" +
                "added soap 5\r\n" +
                "added bread 1\r\n" +
                "subtotal:52.5, discount:0.0, total:52.5\r\n" +
                "offer added\r\n" +
                "subtotal:52.5, discount:10.0, total:42.5\r\n" +
                "added soap 1\r\n" +
                "subtotal:62.5, discount:20.0, total:42.5\r\n" +
                "offer added\r\n" +
                "added bread 1\r\n" +
                "subtotal:65.0, discount:21.25, total:43.75\r\n" +
                "done\r\n", outContent.toString());
    }

}