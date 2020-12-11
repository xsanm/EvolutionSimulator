import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataPanel extends JPanel implements ActionListener {

    SimulationEngine simulationEngine;
    DataManager dataManager;
    GridBagConstraints constraints;
    private JSpinner widthSpinner;
    private JSpinner heightSpinner;
    private JSpinner animalsNumberSpinner;
    private JSpinner grassNumberSpinner;
    private JSpinner startEnergySpinner;
    private JSpinner moveEnergySpinner;
    private JSpinner grassEnergySpinner;
    private JSpinner jungleRatioSpinner;
    private JCheckBox twoMapCheckbox;
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


    public DataPanel(SimulationEngine simulationEngine, DataManager dataManager){
        this.SIMULATE1 = false;
        this.SIMULATE2 = false;
        this.dataManager = dataManager;
        this.simulationEngine = simulationEngine;
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(2,2,2,2);

        //dataPanel.setMaximumSize(new Dimension(300, 300));
        this.setLayout(new GridBagLayout());
        this.setPreferredSize(new Dimension(250, 500));

        widthSpinner = new JSpinner(new SpinnerNumberModel(dataManager.mapWidth, 3, 50, 1));
        heightSpinner = new JSpinner(new SpinnerNumberModel(dataManager.mapHeight, 3, 50, 1));
        animalsNumberSpinner = new JSpinner(new SpinnerNumberModel(dataManager.startAnimalNumber, 0, 1000, 1));
        grassNumberSpinner = new JSpinner(new SpinnerNumberModel(dataManager.startGrassNumber, 0, 1000, 1));
        startEnergySpinner = new JSpinner(new SpinnerNumberModel(dataManager.startEnergy, 0, 5.0, 0.1));
        moveEnergySpinner = new JSpinner(new SpinnerNumberModel(dataManager.moveEnergy, 0, 5.0, 0.1));
        grassEnergySpinner = new JSpinner(new SpinnerNumberModel(dataManager.grassEnergy, 0, 5.0, 0.1));
        jungleRatioSpinner = new JSpinner(new SpinnerNumberModel(dataManager.jungleRatio, 0, 1.0, 0.1));
        twoMapCheckbox = new JCheckBox();


        addGB(new JTextArea("Specify Simulation Data"), 0, 0, 2, 1);

        addGB(new JLabel("Map Width"),  0, 1, 1, 1);
        addGB(widthSpinner,  1, 1, 1, 1);

        addGB(new JLabel("Map Height"),  0, 2, 1, 1);
        addGB(heightSpinner,  1, 2, 1, 1);

        addGB(new JLabel("Starting animal number"),  0, 3, 1, 1);
        addGB(animalsNumberSpinner,  1, 3, 1, 1);

        addGB(new JLabel("Starting grass number"),  0, 4, 1, 1);
        addGB(grassNumberSpinner,  1, 4, 1, 1);

        addGB(new JLabel("Starting Energy"),  0, 5, 1, 1);
        addGB(startEnergySpinner,  1, 5, 1, 1);

        addGB(new JLabel("Move Energy"),  0, 6, 1, 1);
        addGB(moveEnergySpinner,  1, 6, 1, 1);

        addGB(new JLabel("Grass Energy"),  0, 7, 1, 1);
        addGB(grassEnergySpinner,  1, 7, 1, 1);

        addGB(new JLabel("Jungle Ratio"),  0, 8, 1, 1);
        addGB(jungleRatioSpinner,  1, 8, 1, 1);

        addGB(new JLabel("Two Maps"),  0, 9, 1, 1);
        addGB(twoMapCheckbox,  1, 9, 1, 1);


        JSeparator sep1 = new JSeparator();
        addGB(sep1,  0, 10, 2, 1);

        appplyBtn = new JButton("Apply Changes");
        appplyBtn.addActionListener(simulationEngine);
        appplyBtn.addActionListener(this);
        addGB(appplyBtn,  0, 11, 1, 1);

        resetBtn = new JButton("Reset Default");
        resetBtn.addActionListener(simulationEngine);
        resetBtn.addActionListener(this);
        addGB(resetBtn,  1, 11, 1, 1);

        JSeparator sep2 = new JSeparator();
        addGB(sep2,  0, 12, 2, 1);

        addGB(new Label("Set gap between ages [ms]"),  0, 13, 2, 1);
        durationSilder = new JSlider(0, 2000, dataManager.duration);
        durationSilder.setPaintTrack(true);
        durationSilder.setPaintTicks(true);
        durationSilder.setPaintLabels(true);
        durationSilder.setMajorTickSpacing(500);
        durationSilder.setMinorTickSpacing(100);
        durationSilder.addChangeListener(this::speedChanged);
        addGB(durationSilder,  0, 14, 2, 1);

        JSeparator sep3 = new JSeparator();
        addGB(sep3,  0, 15, 2, 1);


        addGB(new Label("Map1 Steering"),  0, 16, 2, 1);

        startBtn1 = new JButton("START 1");
        startBtn1.addActionListener(simulationEngine);
        //startBtn.addActionListener(this);
        addGB(startBtn1,  0, 17, 1, 1);

        stopBtn1 = new JButton("STOP 1");
        stopBtn1.addActionListener(simulationEngine);
        //stopBtn.addActionListener(this);
        addGB(stopBtn1,  1, 17, 1, 1);

        stepBtn1 = new JButton("Make Step 1");
        stepBtn1.addActionListener(simulationEngine);
        //appplyBtn.addActionListener(this);
        addGB(stepBtn1,  0, 18, 1, 1);

        saveBtn1 = new JButton("Save 1");
        saveBtn1.addActionListener(simulationEngine);
        //saveBtn.addActionListener(this);
        addGB(saveBtn1,  1, 18, 1, 1);

        JSeparator sep4 = new JSeparator();
        addGB(sep4,  0, 19, 2, 1);


        addGB(new Label("Map2 Steering"),  0, 20, 2, 1);

        startBtn2 = new JButton("START 2");
        startBtn2.addActionListener(simulationEngine);
        //startBtn.addActionListener(this);
        addGB(startBtn2,  0, 21, 1, 1);

        stopBtn2 = new JButton("STOP 2");
        stopBtn2.addActionListener(simulationEngine);
        //stopBtn.addActionListener(this);
        addGB(stopBtn2,  1, 21, 1, 1);

        stepBtn2 = new JButton("Make Step 2");
        stepBtn2.addActionListener(simulationEngine);
        //appplyBtn.addActionListener(this);
        addGB(stepBtn2,  0, 22, 1, 1);

        saveBtn2 = new JButton("Save 2");
        saveBtn2.addActionListener(simulationEngine);
        //saveBtn.addActionListener(this);
        addGB(saveBtn2,  1, 22, 1, 1);

        JSeparator sep5 = new JSeparator();
        addGB(sep5,  0, 19, 2, 1);


    }

    public void speedChanged(ChangeEvent e){
        dataManager.duration = durationSilder.getValue();
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



    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Apply Changes":
                applyData();
                break;
            case "START 1":
                SIMULATE1 = true;
                startBtn1.setEnabled(false);
                break;
            case "STOP 1":
                SIMULATE1 = false;
                startBtn1.setEnabled(true);
                break;
            case "START 2":
                SIMULATE2 = true;
                startBtn2.setEnabled(false);
                break;
            case "STOP 2":
                SIMULATE2 = false;
                startBtn2.setEnabled(true);
                break;
            case "Make Step 1":
                SIMULATE1 = false;
                startBtn1.setEnabled(false);
                break;
            case "Make Step 2":
                SIMULATE2 = false;
                startBtn2.setEnabled(false);

                break;
            default:
                System.out.println("dgfhdfh");
        }
    }

    private void applyData() {
        dataManager.mapWidth = (int) widthSpinner.getValue();
        dataManager.mapHeight = (int) heightSpinner.getValue();
        dataManager.startAnimalNumber = (int) animalsNumberSpinner.getValue();
        dataManager.startGrassNumber = (int) grassNumberSpinner.getValue();
        dataManager.startEnergy = (double) startEnergySpinner.getValue();
        dataManager.moveEnergy = (double) moveEnergySpinner.getValue();
        dataManager.grassEnergy = (double) grassEnergySpinner.getValue();
        dataManager.jungleRatio = (double) jungleRatioSpinner.getValue();
        dataManager.twoMaps = twoMapCheckbox.isSelected();
        System.out.println("data applyied");
    }

    private void resetData() {
        this.dataManager = new DataManager();
    }
}
