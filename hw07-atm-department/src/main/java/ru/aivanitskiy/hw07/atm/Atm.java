package ru.aivanitskiy.hw07.atm;

public class Atm {
    private AmountState state;
    private final Originator originator;

    public Atm(int defaultAmount) {
        state = new AmountState(defaultAmount);
        originator = new Originator(state);
    }

    public void addAmount(int amount) {
        this.state.setAmount(this.state.getAmount() + amount);
        originator.saveState(this.state);
    }

    public void resetToDefault() {
        state = originator.resetToDefault();
    }

    public int getAmount() {
        return state.getAmount();
    }
}
