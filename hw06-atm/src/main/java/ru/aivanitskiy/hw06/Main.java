package ru.aivanitskiy.hw06;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Bill> bills = new ArrayList<>();

        bills.add(new Bill(Nominal.ONE_THOUSAND));
        bills.add(new Bill(Nominal.ONE_THOUSAND));
        bills.add(new Bill(Nominal.ONE_HUNDRED));

        Atm atm = new Atm(bills);

        System.out.println(atm.getBalance());

        atm.deposit(bills);
        System.out.println(atm.getBalance());

        List<Bill> takenBills = atm.takeMoney(100);
        System.out.println(atm.getBalance());
    }
}
