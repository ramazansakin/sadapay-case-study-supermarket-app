package com.rsakin.sadapay.casestudy;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;

class CashierTest extends BaseTest {

    @Test
    void shouldDoCommandAdd() {
        Cashier cashier = new Cashier();
        cashier.add("add soap 5");
        assertEquals("added soap 5\r\n", outContent.toString());
    }

    @Test
    void shouldGetDoneForSecondCheckout(){
        // to get more commands, I need to create a loop on cashier and do commands until getting second 'checkout'
        Cashier cashier = new Cashier();
        provideInput("checkout\r\ncheckout");
        cashier.work();
        // Try to type 'checkout' twice and see the blow output
        assertEquals("empty cart\r\ndone\r\n", outContent.toString());
    }


}