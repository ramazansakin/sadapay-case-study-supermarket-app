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

}