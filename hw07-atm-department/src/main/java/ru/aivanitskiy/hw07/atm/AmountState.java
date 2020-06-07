package ru.aivanitskiy.hw07.atm;

public class AmountState {
    private int amount = 0;

    public AmountState(int amount) {
        this.amount = amount;
    }

    public AmountState(AmountState state) {
        this.amount = state.amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
