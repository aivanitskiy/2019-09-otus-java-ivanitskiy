package ru.aivanitskiy.hw06;

public class Bill implements Comparable {

    private Nominal nominal;

    public Bill(Nominal nominal) {
        this.nominal = nominal;
    }

    public Nominal getNominal() {
        return nominal;
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Bill)) {
            throw new ClassCastException();
        }

        if(((Bill) o).getNominal().getValue() > this.getNominal().getValue()) {
            return -1;
        } else if (((Bill) o).getNominal().getValue() < this.getNominal().getValue()) {
            return 1;
        }

        return 0;
    }
}
