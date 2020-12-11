import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class StatManager {
    private double averageEnergy;
    private double averageLife;
    private double averageChildren;
    private int dominatingGene;
    private int ages;
    private int animals;
    private int grasses;
    private String statFile;
    private boolean saving;

    public StatManager() {
        resetStats();
    }

    public void resetStats() {
        this.saving = false;
        this.statFile = "";
        this.averageEnergy = 0.0;
        this.averageLife = 0.0;
        this.averageChildren = 0.0;
        this.dominatingGene = 0;
        this.ages = 0;
        this.animals = 0;
        this.grasses = 0;
    }

    public double getAverageChildren() {
        return averageChildren;
    }

    public void setAverageChildren(double averageChildren) {
        this.averageChildren = averageChildren;
    }

    public double getAverageEnergy() {
        return averageEnergy;
    }

    public void setAverageEnergy(double averageEnergy) {
        this.averageEnergy = averageEnergy;
    }

    public double getAverageLife() {
        return this.averageLife;
    }

    public void setAverageLife(double averageLife) {
        this.averageLife = averageLife;
    }

    public int getAges() {
        return ages;
    }

    public void setAges(int ages) {
        this.ages = ages;
    }

    public int getAnimals() {
        return animals;
    }

    public void setAnimals(int animals) {
        this.animals = animals;
    }

    public int getDominatingGene() {
        return dominatingGene;
    }

    public void setDominatingGene(int dominatingGene) {
        this.dominatingGene = dominatingGene;
    }

    public int getGrasses() {
        return grasses;
    }

    public void setGrasses(int grasses) {
        this.grasses = grasses;
    }

    public void setSaving(boolean saving) {
        this.saving = saving;
    }

    public void setStatFile(String statFile) {
        this.statFile = statFile;
    }

    public void saveStat() {
        if (!this.saving) return;
        String source = "{\"animals\"" + ":" + this.getAnimals() + ",\n" +
                "\"grasses\"" + ":" + this.getGrasses() + ",\n" +
                "\"ages\"" + ":" + this.getAges() + ",\n" +
                "\"averageEnergy\"" + ":" + this.getAverageEnergy() + ",\n" +
                "\"averageLife\"" + ":" + this.getAverageLife() + ",\n" +
                "\"averageChildren\"" + ":" + this.getAverageChildren() + ",\n" +
                "\"dominatingGene\"" + ":" + this.getDominatingGene() +
                "}";
        JSONObject jObj = new JSONObject();
        JSONObject jObjNew = new JSONObject(source);
        jObj.put("", jObjNew);

        try {
            PrintWriter out = new PrintWriter(new FileOutputStream(this.statFile, true));
            out.println(jObj.toString());
            out.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
