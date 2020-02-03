package ru.aivanitskiy.hw06;

import ru.aivanitskiy.hw06.exception.CannotGiveMoneyException;
import ru.aivanitskiy.hw06.exception.UnknownNominalException;

import java.util.*;

public class BillStorage {

    private Map<Nominal, List<Bill>> storage = new TreeMap<>(Collections.reverseOrder());

    public BillStorage(Set<Nominal> nominals) {
        for(Nominal nominal: nominals) {
            storage.put(nominal, new ArrayList<>());
        }
    }

    public void insertBills(List<Bill> bills) {
        for (Bill bill: bills) {
            insertBill(bill);
        }
    }

    private void insertBill(Bill bill) {
        if (!storage.containsKey(bill.getNominal())) {
            throw new UnknownNominalException();
        }

        storage.get(bill.getNominal()).add(bill);
    }

    public int getBalance()
    {
        int balance = 0;

        for(Nominal nominal: storage.keySet()) {
            balance += nominal.getValue() * storage.get(nominal).size();
        }

        return balance;
    }

    public List<Bill> takeMoney(int amount) {
        ArrayList<Bill> billsToGive = new ArrayList<>();

        for(Nominal nominal: storage.keySet()) {
            List<Bill> billsCell = storage.get(nominal);
            while (billsCell.size() > 0 && amount / nominal.getValue() >= 1) {
                billsToGive.add(billsCell.remove(billsCell.size() - 1));
                amount -= nominal.getValue();
            }
        }

        if(amount != 0) {
            insertBills(billsToGive);
            throw new CannotGiveMoneyException();
        }

        return billsToGive;
    }
}
