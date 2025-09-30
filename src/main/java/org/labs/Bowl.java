package org.labs;

public class Bowl {
    private Integer foodUnits;

    Bowl() {
        this(0);
    }

    Bowl(Integer foodUnits) {
        this.foodUnits = foodUnits;
    }

    public Integer getFoodUnits() {
        return foodUnits;
    }

    public Integer eat(Integer bite) {
        Integer bitten = foodUnits < bite ? foodUnits : bite;
        foodUnits -= bitten;
        return bitten;
    }

    public Boolean isEmpty() {
        return foodUnits == 0;
    }
}
