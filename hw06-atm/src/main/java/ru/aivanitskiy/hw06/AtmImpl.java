package ru.aivanitskiy.hw06;

import ru.aivanitskiy.hw06.exception.NotEnoughMoneyException;

import java.util.*;

public class AtmImpl implements Atm {

    private final BillStorage storage;

    public AtmImpl(Set<Nominal> nominals) {
        storage = new BillStorage(nominals);
    }

    @Override
    public void deposit(List<Bill> bills) {
        storage.insertBills(bills);
    }

    @Override
    public int getBalance()
    {
        return storage.getBalance();
    }

    @Override
    public List<Bill> takeMoney(int amount) {
        if(amount > getBalance()) {
            throw new NotEnoughMoneyException();
        }

        if(amount <= 0) {
            throw new IllegalArgumentException("Amount must be more than zero");
        }

        List<Bill> billsToGive = storage.takeMoney(amount);

        return billsToGive;
    }
}
