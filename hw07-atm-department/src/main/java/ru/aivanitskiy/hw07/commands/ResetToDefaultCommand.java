package ru.aivanitskiy.hw07.commands;

import ru.aivanitskiy.hw07.atm.Atm;

public class ResetToDefaultCommand {
    private final Atm atm;

    public ResetToDefaultCommand(Atm atm) {
        this.atm = atm;
    }

    public void execute() {
        atm.resetToDefault();
    }
}
