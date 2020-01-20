package ru.aivanitskiy.hw06;

import java.util.*;

public class Atm implements AtmInterface {

    private Map<Nominal, ArrayList<Bill>> storage = new TreeMap<>(Collections.reverseOrder());

    public Atm() {
        this(new ArrayList<Bill>());
    }

    public Atm(List<Bill> bills) {
        deposit(bills);
    }

    @Override
    public void deposit(List<Bill> bills) {
        for (Bill bill: bills) {
            depositBill(bill);
        }
    }

    @Override
    public int getBalance()
    {
        int balance = 0;

        for(Nominal nominal: storage.keySet()) {
            balance += nominal.getValue() * storage.get(nominal).size();
        }

        return balance;
    }

    @Override
    public List<Bill> takeMoney(int amount) {
        if(amount > getBalance()) {
            throw new NotEnoughMoneyException();
        }

        if(amount <= 0) {
            throw new IllegalArgumentException("Amount must be more than zero");
        }

        ArrayList<Bill> billsToGive = new ArrayList<>();


        for(Nominal nominal: storage.keySet()) {
            ArrayList<Bill> billsCell = storage.get(nominal);
            while (billsCell.size() > 0 && amount / nominal.getValue() >= 1) {
                billsToGive.add(billsCell.remove(billsCell.size() - 1));
                amount -= nominal.getValue();
            }
        }

        if(amount != 0) {
            deposit(billsToGive);
            throw new CannotGiveMoneyException();
        }

        return billsToGive;
    }

    private void depositBill(Bill bill) {
        if (!storage.containsKey(bill.getNominal())) {
            storage.put(bill.getNominal(), new ArrayList<Bill>());
        }

        ArrayList<Bill> bills = storage.get(bill.getNominal());
        bills.add(bill);
    }
}
