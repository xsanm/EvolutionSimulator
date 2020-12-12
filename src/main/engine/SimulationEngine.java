package engine;

import data.DataManager;
import data.StatManager;
import gui.MainWindow;
import gui.MapPanel;
import gui.StatPanel;
import map.IWorldMap;
import map.WorldMap;
import objects.Animal;
import objects.Genotype;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SimulationEngine implements IEngine, ActionListener {
    DataManager dataManager;
    MapPanel mapPanel1;
    MapPanel mapPanel2;
    MainWindow m;
    StatPanel statPanel1;
    StatPanel statPanel2;
    StatManager statManager1;
    StatManager statManager2;
    private IWorldMap map1;
    private IWorldMap map2;
    private boolean SIMULATE1;
    private boolean SIMULATE2;
    private Timer stepTimer;

    public SimulationEngine() {
        SIMULATE1 = false;
        SIMULATE2 = false;

        this.dataManager = new DataManager();
        stepTimer = new Timer(dataManager.getDuration(), this::startEngine);
        mapPanel1 = new MapPanel(dataManager, map1);
        mapPanel2 = new MapPanel(dataManager, map2);
        statManager1 = new StatManager();
        statManager2 = new StatManager();
        statPanel1 = new StatPanel(statManager1);
        statPanel2 = new StatPanel(statManager2);

        m = new MainWindow(this, dataManager, mapPanel1, mapPanel2, statPanel1, statPanel2);
        m.setVisible(true);
        initialize();
    }

    public void initialize() {
        statManager1.resetStats();
        statManager2.resetStats();

        this.map1 = new WorldMap(dataManager, mapPanel1, statManager1);
        this.map2 = new WorldMap(dataManager, mapPanel2, statManager2);

        map1.generateAnimals();
        map1.generateGrasses();
        map1.countStats();
        statPanel1.refreshStats();

        if (dataManager.getTwoMaps()) {
            map2.generateAnimals();
            map2.generateGrasses();
            map2.countStats();
            map2.countStats();
            statPanel2.refreshStats();
        }
        m.repaint();
        m.pack();
    }


    public void applyChanges(ActionEvent e) {
        m.steerSecondMap(dataManager.getTwoMaps());
        initialize();
        m.pack();
    }

    public void resetDefault(ActionEvent e) {
        m.steerSecondMap(dataManager.getTwoMaps());
        initialize();
        m.pack();
    }

    public void start1(ActionEvent e) {
        SIMULATE1 = true;
        if (!stepTimer.isRunning()) stepTimer.start();
    }

    public void start2(ActionEvent e) {
        SIMULATE2 = true;
        if (!stepTimer.isRunning()) stepTimer.start();
    }

    public void stop1(ActionEvent e) {
        SIMULATE1 = false;
        if (stepTimer != null && !SIMULATE2) {
            stepTimer.stop();
        }
    }

    public void stop2(ActionEvent e) {
        SIMULATE2 = false;
        if (stepTimer != null && !SIMULATE1) {
            stepTimer.stop();
        }
    }

    public void makeStep1(ActionEvent e) {
        SIMULATE1 = false;
        cycle(map1, statPanel1);
    }

    public void makeStep2(ActionEvent e) {
        SIMULATE2 = false;
        cycle(map2, statPanel2);
    }

    public void save1(ActionEvent e) {
        JFileChooser j = new JFileChooser();

        int r = j.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            statManager1.setSaving(true);
            statManager1.setStatFile(j.getSelectedFile().getAbsolutePath());
        }
    }

    public void save2(ActionEvent e) {
        JFileChooser j = new JFileChooser();

        int r = j.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            statManager2.setSaving(true);
            statManager2.setStatFile(j.getSelectedFile().getAbsolutePath());
        }
    }

    private void startEngine(ActionEvent e) {
        if (SIMULATE1) cycle(map1, statPanel1);
        if (SIMULATE2) if (dataManager.getTwoMaps()) cycle(map2, statPanel2);
    }

    public void speedChanged() {
        if (stepTimer != null && stepTimer.isRunning()) {
            this.stepTimer.setDelay(dataManager.getDuration());
        }
    }

    private void cycle(IWorldMap map, StatPanel statPanel) {
        map.deleteDead();
        map.rotate();
        map.move();
        map.eat();
        map.procreate();
        map.addGrass();
        map.redrawAnimals();
        map.countStats();
        statPanel.refreshStats();
        m.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
