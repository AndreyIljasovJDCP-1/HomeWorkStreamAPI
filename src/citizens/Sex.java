package citizens;

public enum Sex {
    MAN(65, 65), WOMAN(60, 75);

    private int retirementAge;
    private int lifeExpectancy;

    Sex(int retirementAge, int lifeExpectancy) {
        this.retirementAge = retirementAge;
        this.lifeExpectancy = lifeExpectancy;
    }

    public int getLifeExpectancy() {
        return lifeExpectancy;
    }

    public int getRetirementAge() {
        return retirementAge;
    }

    public void setRetirementAge(int retirementAge) {
        this.retirementAge = retirementAge;
    }

    public void setLifeExpectancy(int lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }
}
