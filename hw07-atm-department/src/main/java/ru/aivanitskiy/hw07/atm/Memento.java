package ru.aivanitskiy.hw07.atm;

public class Memento {
    private final AmountState state;

    public Memento(AmountState state) {
        this.state = new AmountState(state);
    }

    public AmountState getState() {
        return state;
    }
}
