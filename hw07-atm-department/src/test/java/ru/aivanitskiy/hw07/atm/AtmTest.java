package ru.aivanitskiy.hw07.atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AtmTest {
    private Atm atm;

    @BeforeEach
    private void prepare() {
        atm = new Atm(100);
    }

    @Test
    public void getDefaultBalance() {
        assertEquals(100, atm.getAmount());
    }

    @Test
    public void resetDefaultBalance() {
        assertEquals(100, atm.getAmount());

        atm.addAmount(200);
        assertNotEquals(100, atm.getAmount());

        atm.resetToDefault();
        assertEquals(100, atm.getAmount());
    }
}