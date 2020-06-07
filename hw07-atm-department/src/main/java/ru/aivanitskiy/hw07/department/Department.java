package ru.aivanitskiy.hw07.department;

import ru.aivanitskiy.hw07.atm.Atm;
import ru.aivanitskiy.hw07.commands.ResetToDefaultCommand;

import java.util.ArrayList;
import java.util.List;

public class Department {

    private final List<Atm> atms = new ArrayList<>();

    public void registerAtm(Atm atm) {
        atms.add(atm);
    }

    public void unregisterAtm(Atm atm) {
        atms.remove(atm);
    }

    public int getSumAmount() {
        int amount = 0;

        for(Atm atm: atms) {
            amount += atm.getAmount();
        }

        return amount;
    }

    public void resetToDefault()
    {
        for(Atm atm: atms) {
            ResetToDefaultCommand command = new ResetToDefaultCommand();
            command.execute(atm);
        }
    }
}
