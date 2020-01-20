package ru.aivanitskiy.hw06;

public enum Nominal {
    ONE_HUNDRED(100),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000);

    private final int value;

    public int getValue() {
        return value;
    }

    private Nominal(int value) {
        this.value = value;
    }
}
