package org.rkoubsky.chapter3.visibility.threadconfinment.stackconfinment;

public class Animal {
    private Species species;
    private Gender gender;

    public Species getSpecies() {
        return this.species;
    }

    public Gender getGender() {
        return this.gender;
    }

    public boolean isPotentialMate(final Animal other) {
        return this.species == other.species && this.gender != other.gender;
    }
}
