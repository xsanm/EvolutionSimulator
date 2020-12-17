package gui;

import data.DataManager;
import engine.SimulationEngine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataPanel extends JPanel implements ActionListener {

    SimulationEngine simulationEngine;
    DataManager dataManager;
    GridBagConstraints constraints;
    JButton appplyBtn;
    JButton resetBtn;
    JButton startBtn1;
    JButton stopBtn1;
    JButton stepBtn1;
    JButton saveBtn1;
    JButton startBtn2;
    JButton stopBtn2;
    JButton stepBtn2;
    JButton saveBtn2;
    JSlider durationSilder;
    boolean SIMULATE1;
    boolean SIMULATE2;
    private JSpinner widthSpinner;
    private JSpinner heightSpinner;
    private JSpinner animalsNumberSpinner;
    private JSpinner grassNumberSpinner;
    private JSpinner startEnergySpinner;
    private JSpinner moveEnergySpinner;
    private JSpinner grassEnergySpinner;
    private JSpinner jungleRatioSpinner;
    private JCheckBox twoMapCheckbox;


    public DataPanel(SimulationEngine simulationEngine, DataManager dataManager) {
        this.SIMULATE1 = false;
        this.SIMULATE2 = false;
        this.dataManager = dataManager;
        this.simulationEngine = simulationEngine;
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(2, 2, 2, 2);

        //dataPanel.setMaximumSize(new Dimension(300, 300));
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(250, 500));

        widthSpinner = new JSpinner(new SpinnerNumberModel(dataManager.getMapWidth(), 3, 50, 1));
        heightSpinner = new JSpinner(new SpinnerNumberModel(dataManager.getMapHeight(), 3, 50, 1));
        animalsNumberSpinner = new JSpinner(new SpinnerNumberModel(dataManager.getStartAnimalNumber(), 0, 1000, 1));
        grassNumberSpinner = new JSpinner(new SpinnerNumberModel(dataManager.getStartGrassNumber(), 0, 1000, 1));
        startEnergySpinner = new JSpinner(new SpinnerNumberModel(dataManager.getStartEnergy(), 0, 5.0, 0.1));
        moveEnergySpinner = new JSpinner(new SpinnerNumberModel(dataManager.getMoveEnergy(), 0, 5.0, 0.1));
        grassEnergySpinner = new JSpinner(new SpinnerNumberModel(dataManager.getGrassEnergy(), 0, 5.0, 0.1));
        jungleRatioSpinner = new JSpinner(new SpinnerNumberModel(dataManager.getJungleRatio(), 0, 1.0, 0.1));
        twoMapCheckbox = new JCheckBox();


        addGB(new JTextArea("Specify Simulation Data"), 0, 0, 2, 1);

        addGB(new JLabel("Map Width"), 0, 1, 1, 1);
        addGB(widthSpinner, 1, 1, 1, 1);

        addGB(new JLabel("Map Height"), 0, 2, 1, 1);
        addGB(heightSpinner, 1, 2, 1, 1);

        addGB(new JLabel("Starting animal number"), 0, 3, 1, 1);
        addGB(animalsNumberSpinner, 1, 3, 1, 1);

        addGB(new JLabel("Starting grass number"), 0, 4, 1, 1);
        addGB(grassNumberSpinner, 1, 4, 1, 1);

        addGB(new JLabel("Starting Energy"), 0, 5, 1, 1);
        addGB(startEnergySpinner, 1, 5, 1, 1);

        addGB(new JLabel("Move Energy"), 0, 6, 1, 1);
        addGB(moveEnergySpinner, 1, 6, 1, 1);

        addGB(new JLabel("Grass Energy"), 0, 7, 1, 1);
        addGB(grassEnergySpinner, 1, 7, 1, 1);

        addGB(new JLabel("Jungle Ratio"), 0, 8, 1, 1);
        addGB(jungleRatioSpinner, 1, 8, 1, 1);

        addGB(new JLabel("Two Maps"), 0, 9, 1, 1);
        addGB(twoMapCheckbox, 1, 9, 1, 1);

        JSeparator sep1 = new JSeparator();
        addGB(sep1, 0, 10, 2, 1);

        appplyBtn = new JButton("Apply Changes");
        appplyBtn.addActionListener(simulationEngine::applyChanges);
        appplyBtn.addActionListener(this::applyChanges);
        addGB(appplyBtn, 0, 11, 1, 1);

        resetBtn = new JButton("Reset Default");
        resetBtn.addActionListener(simulationEngine::resetDefault);
        addGB(resetBtn, 1, 11, 1, 1);

        JSeparator sep2 = new JSeparator();
        addGB(sep2, 0, 12, 2, 1);

        addGB(new Label("Set gap between ages [ms]"), 0, 13, 2, 1);
        durationSilder = new JSlider(0, 2000, dataManager.getDuration());
        durationSilder.setPaintTrack(true);
        durationSilder.setPaintTicks(true);
        durationSilder.setPaintLabels(true);
        durationSilder.setMajorTickSpacing(500);
        durationSilder.setMinorTickSpacing(100);
        durationSilder.addChangeListener(this::speedChanged);
        addGB(durationSilder, 0, 14, 2, 1);

        JSeparator sep3 = new JSeparator();
        addGB(sep3, 0, 15, 2, 1);


        addGB(new Label("Map1 Steering"), 0, 16, 2, 1);

        startBtn1 = new JButton("START 1");
        startBtn1.addActionListener(simulationEngine::start1);
        startBtn1.addActionListener(this::start1);
        addGB(startBtn1, 0, 17, 1, 1);

        stopBtn1 = new JButton("STOP 1");
        stopBtn1.addActionListener(simulationEngine::stop1);
        stopBtn1.addActionListener(this::stop1);
        stopBtn1.setEnabled(false);
        addGB(stopBtn1, 1, 17, 1, 1);

        stepBtn1 = new JButton("Make Step 1");
        stepBtn1.addActionListener(simulationEngine::makeStep1);
        addGB(stepBtn1, 0, 18, 1, 1);

        saveBtn1 = new JButton("Save 1");
        saveBtn1.addActionListener(simulationEngine::save1);
        addGB(saveBtn1, 1, 18, 1, 1);

        JSeparator sep4 = new JSeparator();
        addGB(sep4, 0, 19, 2, 1);

        addGB(new Label("Map2 Steering"), 0, 20, 2, 1);

        startBtn2 = new JButton("START 2");
        startBtn2.addActionListener(simulationEngine::start2);
        startBtn2.addActionListener(this::start2);
        startBtn2.setVisible(false);
        addGB(startBtn2, 0, 21, 1, 1);

        stopBtn2 = new JButton("STOP 2");
        stopBtn2.addActionListener(simulationEngine::stop2);
        stopBtn2.addActionListener(this::stop2);
        stopBtn2.setEnabled(false);
        stopBtn2.setVisible(false);
        addGB(stopBtn2, 1, 21, 1, 1);

        stepBtn2 = new JButton("Make Step 2");
        stepBtn2.addActionListener(simulationEngine::makeStep2);
        stepBtn2.setVisible(false);
        addGB(stepBtn2, 0, 22, 1, 1);

        saveBtn2 = new JButton("Save 2");
        saveBtn2.addActionListener(simulationEngine::save2);
        saveBtn2.setVisible(false);
        addGB(saveBtn2, 1, 22, 1, 1);

        JSeparator sep5 = new JSeparator();
        addGB(sep5, 0, 19, 2, 1);
    }

    public void speedChanged(ChangeEvent e) {
        dataManager.setDuration(durationSilder.getValue());
        simulationEngine.speedChanged();
    }

    void addGB(Component component, int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;

        constraints.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
        constraints.fill = (x == 0) ? GridBagConstraints.BOTH : GridBagConstraints.HORIZONTAL;

        constraints.weightx = (x == 0) ? 0.1 : 1.0;

        this.add(component, constraints);
    }

    public void applyChanges(ActionEvent e) {
        applyData();
        if (dataManager.getTwoMaps()) {
            startBtn2.setVisible(true);
            stopBtn2.setVisible(true);
            stepBtn2.setVisible(true);
            saveBtn2.setVisible(true);
        } else {
            startBtn2.setVisible(false);
            stopBtn2.setVisible(false);
            stepBtn2.setVisible(false);
            saveBtn2.setVisible(false);
        }
    }

    public void start1(ActionEvent e) {
        SIMULATE1 = true;
        startBtn1.setEnabled(false);
        stopBtn1.setEnabled(true);
        appplyBtn.setEnabled(false);
        resetBtn.setEnabled(false);
        stepBtn1.setEnabled(false);
        saveBtn1.setEnabled(false);
        this.repaint();
    }

    public void start2(ActionEvent e) {
        SIMULATE2 = true;
        startBtn2.setEnabled(false);
        stopBtn2.setEnabled(true);
        appplyBtn.setEnabled(false);
        resetBtn.setEnabled(false);
        stepBtn2.setEnabled(false);
        saveBtn2.setEnabled(false);
    }

    public void stop1(ActionEvent e) {
        SIMULATE1 = false;
        startBtn1.setEnabled(true);
        stepBtn1.setEnabled(true);
        stopBtn1.setEnabled(false);
        saveBtn1.setEnabled(true);
        if (!SIMULATE2) {
            appplyBtn.setEnabled(true);
            resetBtn.setEnabled(true);
        }
    }

    public void stop2(ActionEvent e) {
        SIMULATE2 = false;
        startBtn2.setEnabled(true);
        stepBtn2.setEnabled(true);
        stopBtn2.setEnabled(false);
        saveBtn2.setEnabled(true);
        if (!SIMULATE1) {
            appplyBtn.setEnabled(true);
            resetBtn.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    private void applyData() {
        dataManager.setMapWidth((int) widthSpinner.getValue());
        dataManager.setMapHeight((int) heightSpinner.getValue());
        dataManager.setStartAnimalNumber((int) animalsNumberSpinner.getValue());
        dataManager.setStartGrassNumber((int) grassNumberSpinner.getValue());
        dataManager.setStartEnergy((double) startEnergySpinner.getValue());
        dataManager.setMoveEnergy((double) moveEnergySpinner.getValue());
        dataManager.setGrassEnergy((double) grassEnergySpinner.getValue());
        dataManager.setJungleRatio((double) jungleRatioSpinner.getValue());
        dataManager.setTwoMaps(twoMapCheckbox.isSelected());
    }

}
