package gui;

import data.StatManager;

import javax.swing.*;

public class StatPanel extends JList {
    StatManager statManager;

    public StatPanel(StatManager statManager) {
        super(new DefaultListModel<>());
        this.statManager = statManager;
        refreshStats();

    }

    public void refreshStats() {
        double scale = 10000;
        DefaultListModel<String> l1 = new DefaultListModel<>();
        l1.addElement("Animals alive: " + statManager.getAnimals());
        l1.addElement("Grasses: " + statManager.getGrasses());
        l1.addElement("Ages: " + statManager.getAges());
        l1.addElement("Average energy: " + Math.round(statManager.getAverageEnergy() * scale) / scale);
        l1.addElement("Average years of dead animals: " + Math.round(statManager.getAverageLife() * scale) / scale);
        l1.addElement("Average childre of alive animals: " + Math.round(statManager.getAverageChildren() * scale) / scale);
        l1.addElement("Dominating genotype: " + String.valueOf(statManager.getDominatingGene()));
        this.setModel(l1);
    }
}
