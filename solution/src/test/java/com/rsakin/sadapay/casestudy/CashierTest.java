package com.rsakin.sadapay.casestudy;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;

class CashierTest extends BaseTest {

    Cashier cashier = new Cashier();

    @Test
    void shouldDoCommandAdd() {
        Cashier cashier = new Cashier();
        cashier.add("add soap 5");
        assertEquals("added soap 5\r\n", outContent.toString());
    }

    @Test
    void shouldGetDoneForSecondCheckout(){
        // to get more commands, I need to create a loop on cashier and do commands until getting second 'checkout'
        provideInput("checkout\r\ncheckout");
        cashier.work();
        // Try to type 'checkout' twice and see the blow output
        assertEquals("empty cart\r\ndone\r\n", outContent.toString());
    }

    // First we need to checkout to start shopping
    @Test
    void shouldAddItemToCart() {
        // we need to create a loop to get commands and try to get checkout first to start shopping
        provideInput("checkout\r\nadd bread 5\r\ncheckout");
        cashier.work();
        // write add to see the below error message
        assertEquals("empty cart\r\nadded bread 5\r\ndone\r\n", outContent.toString());
    }

    // need to see bill details when I run 'bill' command
    @Test
    void shouldRunBillCommandToSeeDetails(){
        provideInput("checkout\r\nadd soap 5\r\nbill\r\ncheckout");
        cashier.work();
        assertEquals("empty cart\r\nadded soap 5\r\nsubtotal:50, discount:0.0, total:50.0\r\ndone\r\n", outContent.toString());
    }


}