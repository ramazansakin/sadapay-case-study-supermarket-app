package com.rsakin.sadapay.casestudy;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {

    @Test
    void shouldGetErrorWhenNoSuchFileState() throws IOException {
        Inventory inventory = new Inventory("xxx.csv");
    }

}