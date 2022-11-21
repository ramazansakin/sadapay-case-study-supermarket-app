package com.rsakin.sadapay.casestudy;

import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;

class InventoryTest extends BaseTest {

    @Test
    void shouldGetErrorWhenNoSuchFileState() {
        Inventory inventory = new Inventory("xxx.csv");
        assertEquals("There is no such file [ xxx.csv ]\r\n", errContent.toString());
    }

}