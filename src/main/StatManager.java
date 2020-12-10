public class StatManager {
    private double averageEnergy;
    private double averageLife;
    private double averageChildren;
    private int dominatingGene;
    private int ages;
    private int animals;
    private int grasses;

    public StatManager() {
        resetStats();
    }

    public void resetStats() {
        this.averageEnergy = 0.0;
        this.averageLife = 0.0;
        this.averageChildren = 0.0;
        this.dominatingGene = 0;
        this.ages = 0;
        this.animals = 0;
        this.grasses = 0;
    }


    public void setAges(int ages) {
        this.ages = ages;
    }

    public void setAnimals(int animals) {
        this.animals = animals;
    }

    public void setAverageChildren(double averageChildren) {
        this.averageChildren = averageChildren;
    }

    public void setAverageEnergy(double averageEnergy) {
        this.averageEnergy = averageEnergy;
    }

    public void setAverageLife(double averageLife) {
        this.averageLife = averageLife;
    }

    public void setDominatingGene(int dominatingGene) {
        this.dominatingGene = dominatingGene;
    }

    public void setGrasses(int grasses) {
        this.grasses = grasses;
    }

    public double getAverageChildren() {
        return averageChildren;
    }

    public double getAverageEnergy() {
        return averageEnergy;
    }

    public double getAverageLife() {
        return this.averageLife;
    }

    public int getAges() {
        return ages;
    }

    public int getAnimals() {
        return animals;
    }

    public int getDominatingGene() {
        return dominatingGene;
    }

    public int getGrasses() {
        return grasses;
    }
}
