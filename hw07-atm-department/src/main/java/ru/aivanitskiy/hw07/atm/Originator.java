package ru.aivanitskiy.hw07.atm;

import java.util.ArrayList;
import java.util.List;

public class Originator {
    private final List<Memento> history = new ArrayList<>();

    public Originator(AmountState state) {
        this.saveState(state);
    }

    public void saveState(AmountState state) {
        history.add(new Memento(state));
    }

    public AmountState resetToDefault() {
        AmountState defaultState = history.get(0).getState();
        history.clear();
        this.saveState(defaultState);

        return defaultState;
    }
}
