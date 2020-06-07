package ru.aivanitskiy.hw07.commands;

import ru.aivanitskiy.hw07.atm.Atm;

public class ResetToDefaultCommand implements AtmCommand {
    public void execute(Atm atm) {
        atm.resetToDefault();
    }
}
