package com.rsakin.sadapay.casestudy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

class SuperMarketTest {
    // To catch the stdout and stderr messages and assert with expected ones
    protected final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    protected final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    protected final PrintStream originalOut = System.out;
    protected final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    // First we have inventory file and we need to read all the lines properly
    @Test
    void shouldGetInventoryFileAndPrintContent() {
        // supermarket init with inventory file
        SuperMarket superMarket = new SuperMarket(".\\..\\functional_spec\\fixtures\\inventory.csv");

    }

    @Test
    void shouldRunCommandCheckout() {
        SuperMarket superMarket = new SuperMarket(".\\..\\functional_spec\\fixtures\\inventory.csv");
        superMarket.doCommand("checkout");
        assertEquals("empty cart\r\n", outContent.toString());
    }

    // First we need to checkout to start shopping
    @Test
    void shouldNotAddItemToCart_NeedToCheckoutFirst() {
        // we need to create a loop to get commands and try to get checkout first to start shopping
        SuperMarket superMarket = new SuperMarket(".\\..\\functional_spec\\fixtures\\inventory.csv");
        superMarket.doCommand("add soap 5");
        assertEquals("You need to 'checkout' first to start shopping!\r\n", outContent.toString());
    }

}