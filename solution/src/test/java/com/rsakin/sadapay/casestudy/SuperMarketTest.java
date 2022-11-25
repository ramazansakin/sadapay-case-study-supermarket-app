package com.rsakin.sadapay.casestudy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static junit.framework.TestCase.assertEquals;

class SuperMarketTest extends BaseTest {

    SuperMarket superMarket = SuperMarket.getSuperMarket("inventory.csv");

    @BeforeEach
    public void resetSingleton() throws NoSuchFieldException, IllegalAccessException {
        Field instance = SuperMarket.class.getDeclaredField("superMarket");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    // First we have inventory file and we need to read all the lines properly
    @Test
    @DisplayName("First we have inventory file and we need to read all the lines properly")
    void shouldGetInventoryFileAndPrintContent() {
        // supermarket init with inventory file
        provideInput("checkout\ncheckout");
        superMarket.work(null);
    }

    // After refactor, Now we need to see 'empty cart' and 'done' outputs after two checkouts,
    @Test
    void shouldRunCommandCheckout() {
        provideInput("checkout\ncheckout");
        superMarket.work(null);
        assertEquals("empty cart\r\ndone\r\n", outContent.toString());
    }

    // First we need to checkout to start shopping
    @Test
    void shouldNotAddItemToCart_NeedToCheckoutFirst() {
        // we need to create a loop to get commands and try to get checkout first to start shopping
        provideInput("add soap 5\ncheckout\ncheckout");
        superMarket.work(null);
        // write add to see the below error message
        assertEquals("You need to 'checkout' first to start shopping!\r\n", errContent.toString());
    }

    // need to see bill details when I run 'bill' command
    @Test
    void shouldRunOfferCommandTodefineOffer_BUY_2_GET_1_FREE() {
        provideInput("checkout\nadd soap 5\noffer buy_2_get_1_free soap\nbill\ncheckout");
        superMarket.work(null);
        assertEquals("empty cart\r\nadded soap 5\r\noffer added\r\nsubtotal:50.00, discount:10.00, total:40.00\ndone\r\n", outContent.toString());
    }

    // need to see bill details when I run 'bill' command
    @Test
    void shouldRunOfferCommandTodefineOffer_BUY_1_GET_HALF_OFF() {
        provideInput("checkout\nadd soap 4\noffer buy_1_get_half_off soap\nbill\ncheckout");
        superMarket.work(null);
        assertEquals("empty cart\r\nadded soap 4\r\noffer added\r\nsubtotal:40.00, discount:10.00, total:30.00\ndone\r\n", outContent.toString());
    }

    // need to see the whole test flow in doc can run properly
    @Test
    void shouldWorkAllTheSampleCasesRunningProperly() {
        provideInput("checkout\nadd soap 5\nadd bread 1\nbill\noffer buy_2_get_1_free soap\nbill\nadd soap 1\nbill\noffer buy_1_get_half_off bread\nadd bread 1\nbill\ncheckout");
        superMarket.work(null);
        assertEquals("empty cart\r\n" +
                "added soap 5\r\n" +
                "added bread 1\r\n" +
                "subtotal:52.50, discount:0.00, total:52.50\n" +
                "offer added\r\n" +
                "subtotal:52.50, discount:10.00, total:42.50\n" +
                "added soap 1\r\n" +
                "subtotal:62.50, discount:20.00, total:42.50\n" +
                "offer added\r\n" +
                "added bread 1\r\n" +
                "subtotal:65.00, discount:21.25, total:43.75\n" +
                "done\r\n", outContent.toString());
    }

    // need to see the whole test flow with commands file in doc can run properly
    @Test
    void shouldWorkWithCommandFile() {
        superMarket.work("commands.txt");
        assertEquals("empty cart\r\n" +
                "added soap 5\r\n" +
                "added bread 1\r\n" +
                "subtotal:52.50, discount:0.00, total:52.50\n" +
                "offer added\r\n" +
                "subtotal:52.50, discount:10.00, total:42.50\n" +
                "added soap 1\r\n" +
                "subtotal:62.50, discount:20.00, total:42.50\n" +
                "offer added\r\n" +
                "added bread 1\r\n" +
                "subtotal:65.00, discount:21.25, total:43.75\n" +
                "done\r\n", outContent.toString());
    }

    @Test
    void shouldRemoveItemOnList() {
        provideInput("checkout\nadd soap 3\nbill\noffer buy_2_get_1_free soap\nbill\nremove soap 1\nbill\ncheckout");
        superMarket.work(null);
        assertEquals("empty cart\r\n" +
                "added soap 3\r\n" +
                "subtotal:30.00, discount:0.00, total:30.00\n" +
                "offer added\r\n" +
                "subtotal:30.00, discount:10.00, total:20.00\n" +
                "removed soap 1\r\n" +
                "subtotal:20.00, discount:0.00, total:20.00\n" +
                "done\r\n", outContent.toString());
    }

    @Test
    void shouldRemoveItemOnList2() {
        provideInput("checkout\nadd soap 3\nbill\noffer buy_2_get_1_free soap\nbill\nremove soap 1\nbill\n" +
                "add bread 2\noffer buy_1_get_half_off bread\nbill\nremove bread 1\nbill\ncheckout");
        superMarket.work(null);
        assertEquals("empty cart\r\n" +
                "added soap 3\r\n" +
                "subtotal:30.00, discount:0.00, total:30.00\n" +
                "offer added\r\n" +
                "subtotal:30.00, discount:10.00, total:20.00\n" +
                "removed soap 1\r\n" +
                "subtotal:20.00, discount:0.00, total:20.00\n" +
                "added bread 2\r\n" +
                "offer added\r\n" +
                "subtotal:25.00, discount:1.25, total:23.75\n" +
                "removed bread 1\r\n" +
                "subtotal:22.50, discount:0.00, total:22.50\n" +
                "done\r\n", outContent.toString());
    }

    @Test
    @DisplayName("I need to see that I can add offer called buy_4_get_cheapest and see the discount")
    void shouldRunOfferCommandToDefineOffer_BUY_4_CHEAPEST() {
        provideInput("checkout\nadd soap 1\nadd bread 1\nadd soda 1\nadd cola 1\noffer buy_4_get_cheapest\nbill\ncheckout");
        superMarket.work(null);
        assertEquals("empty cart\r\nadded soap 1\r\nadded bread 1\r\nadded soda 1\r\nadded cola 1\r\noffer added\r\n" +
                "subtotal:18.50, discount:14.50, total:4.00\ndone\r\n", outContent.toString());
    }


}