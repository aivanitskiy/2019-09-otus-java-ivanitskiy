package ru.aivanitskiy.hw06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aivanitskiy.hw06.exception.CannotGiveMoneyException;
import ru.aivanitskiy.hw06.exception.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class AtmImplTest {

    private AtmImpl atm;

    @BeforeEach
    private void prepare() {
        Set<Nominal> nominals = new HashSet<>();

        nominals.add(Nominal.ONE_THOUSAND);
        nominals.add(Nominal.FIVE_HUNDRED);
        nominals.add(Nominal.ONE_HUNDRED);

        atm = new AtmImpl(nominals);
    }

    @Test
    public void getBalance() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(Nominal.ONE_THOUSAND));
        bills.add(new Bill(Nominal.ONE_THOUSAND));
        bills.add(new Bill(Nominal.ONE_THOUSAND));
        bills.add(new Bill(Nominal.ONE_THOUSAND));

        bills.add(new Bill(Nominal.FIVE_HUNDRED));
        bills.add(new Bill(Nominal.FIVE_HUNDRED));
        bills.add(new Bill(Nominal.FIVE_HUNDRED));

        bills.add(new Bill(Nominal.ONE_HUNDRED));
        bills.add(new Bill(Nominal.ONE_HUNDRED));

        atm.deposit(bills);
        assertEquals(5700,  atm.getBalance());
    }

    @Test
    public void getBalanceEmpty() {
        assertEquals(0,  atm.getBalance());
    }

    @Test
    public void takeMoneyTooMuch() {
        assertThrows(NotEnoughMoneyException.class, () -> atm.takeMoney(20000));
    }

    @Test
    public void takeMoneyNegative() {
        assertThrows(IllegalArgumentException.class, () -> atm.takeMoney(-1));
        assertThrows(IllegalArgumentException.class, () -> atm.takeMoney(0));
    }

    @Test
    public void cantGiveMoney() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(Nominal.ONE_THOUSAND));
        atm.deposit(bills);

        assertThrows(CannotGiveMoneyException.class, () -> atm.takeMoney(100));
    }

    @Test
    public void giveMoney() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(Nominal.ONE_THOUSAND));
        bills.add(new Bill(Nominal.ONE_THOUSAND));
        bills.add(new Bill(Nominal.ONE_HUNDRED));
        bills.add(new Bill(Nominal.ONE_HUNDRED));

        atm.deposit(bills);
        atm.takeMoney(1100);
        assertEquals(1100, atm.getBalance());
    }
}