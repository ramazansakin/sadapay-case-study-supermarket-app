package com.rsakin.sadapay.casestudy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BaseTest {
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
}
