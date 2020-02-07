package ru.aivanitskiy.hw06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aivanitskiy.hw06.exception.UnknownNominalException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AtmImplNotAllNominalsTest {

    private AtmImpl atm;

    @BeforeEach
    private void prepare() {
        Set<Nominal> nominals = new HashSet<>();

        nominals.add(Nominal.FIVE_HUNDRED);
        nominals.add(Nominal.ONE_HUNDRED);

        atm = new AtmImpl(nominals);
    }

    @Test
    public void unknownNominal() {
        List<Bill> bills = new ArrayList<>();
        bills.add(new Bill(Nominal.ONE_THOUSAND));
        bills.add(new Bill(Nominal.FIVE_HUNDRED));
        bills.add(new Bill(Nominal.ONE_HUNDRED));

        assertThrows(UnknownNominalException.class, () -> atm.deposit(bills));
    }
}