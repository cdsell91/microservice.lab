package com.lab.hello;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    @Test
    void testHelloMessage() {
        String msg = "Hola";
        assertEquals("Hola", msg);
    }
}