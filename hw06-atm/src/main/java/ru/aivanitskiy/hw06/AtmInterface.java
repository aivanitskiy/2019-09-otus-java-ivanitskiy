package ru.aivanitskiy.hw06;

import java.util.List;

public interface AtmInterface {
    public void deposit(List<Bill> bills);

    public int getBalance();

    public List<Bill> takeMoney(int amount);
}
