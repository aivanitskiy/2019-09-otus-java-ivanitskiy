package ru.aivanitskiy.hw07.department;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aivanitskiy.hw07.atm.Atm;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {
    private Department department;

    @BeforeEach
    private void prepare() {
        department = new Department();
    }

    @Test
    public void resetToDefault() {
        Atm atm1 = new Atm(100);
        Atm atm2 = new Atm(200);
        Atm atm3 = new Atm(300);
        Atm atm4 = new Atm(400);
        Atm atm5 = new Atm(500);

        department.registerAtm(atm1);
        department.registerAtm(atm2);
        department.registerAtm(atm3);
        department.registerAtm(atm4);
        department.registerAtm(atm5);

        assertEquals(1500, department.getSumAmount());

        atm1.addAmount(1000);
        atm2.addAmount(2000);
        atm3.addAmount(3000);
        atm4.addAmount(4000);
        atm5.addAmount(5000);

        assertEquals(16500, department.getSumAmount());

        department.resetToDefault();

        assertEquals(1500, department.getSumAmount());

        department.unregisterAtm(atm3);

        assertEquals(1200, department.getSumAmount());
    }
}