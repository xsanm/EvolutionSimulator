import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataPanel extends JPanel implements ActionListener {
    SimulationEngine simulationEngine;
    DataManager dataManager;
    GridBagConstraints constraints;
    JPanel dataPanel;
    private JSpinner widthSpinner;
    private JSpinner heightSpinner;
    private JSpinner animalsNumberSpinner;
    private JSpinner startEnergySpinner;
    private JSpinner moveEnergySpinner;
    private JSpinner grassEnergySpinner;
    private JSpinner jungleRatioSpinner;
    private JCheckBox twoMapCheckbox;
    JButton appplyBtn;
    JButton resetBtn;
    JButton startBtn;
    JButton stopBtn;
    JButton stepBtn;
    JButton saveBtn;


    public DataPanel(SimulationEngine simulationEngine, DataManager dataManager){
        this.dataManager = dataManager;
        this.simulationEngine = simulationEngine;
        constraints = new GridBagConstraints();
        constraints.insets = new Insets(2,2,2,2);

        dataPanel = new JPanel();
        //dataPanel.setMaximumSize(new Dimension(300, 300));
        dataPanel.setLayout(new GridBagLayout());
        dataPanel.setPreferredSize(new Dimension(250, 500));

        widthSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 50, 1));
        heightSpinner = new JSpinner(new SpinnerNumberModel(10, 0, 50, 1));
        animalsNumberSpinner = new JSpinner(new SpinnerNumberModel(5, 0, 50, 1));
        startEnergySpinner = new JSpinner(new SpinnerNumberModel(2.5, 0, 5.0, 0.1));
        moveEnergySpinner = new JSpinner(new SpinnerNumberModel(0.5, 0, 3.0, 0.1));
        grassEnergySpinner = new JSpinner(new SpinnerNumberModel(1.0, 0, 3.0, 0.1));
        jungleRatioSpinner = new JSpinner(new SpinnerNumberModel(0.50, 0, 1.0, 0.1));
        twoMapCheckbox = new JCheckBox();


        addGB(new JTextArea("Specify Simulation Data"), 0, 0, 2, 1);

        addGB(new JLabel("Map Width"),  0, 1, 1, 1);
        addGB(widthSpinner,  1, 1, 1, 1);

        addGB(new JLabel("Map Height"),  0, 2, 1, 1);
        addGB(heightSpinner,  1, 2, 1, 1);

        addGB(new JLabel("Starting animal number"),  0, 3, 1, 1);
        addGB(animalsNumberSpinner,  1, 3, 1, 1);

        addGB(new JLabel("Starting Energy"),  0, 4, 1, 1);
        addGB(startEnergySpinner,  1, 4, 1, 1);

        addGB(new JLabel("Move Energy"),  0, 5, 1, 1);
        addGB(moveEnergySpinner,  1, 5, 1, 1);

        addGB(new JLabel("Grass Energy"),  0, 6, 1, 1);
        addGB(grassEnergySpinner,  1, 6, 1, 1);

        addGB(new JLabel("Jungle Ratio"),  0, 7, 1, 1);
        addGB(jungleRatioSpinner,  1, 7, 1, 1);

        addGB(new JLabel("Two Maps"),  0, 8, 1, 1);
        addGB(twoMapCheckbox,  1, 8, 1, 1);


        JSeparator sep1 = new JSeparator();
        addGB(sep1,  0, 9, 2, 1);

        appplyBtn = new JButton("Apply Changes");
        appplyBtn.addActionListener(simulationEngine);
        appplyBtn.addActionListener(this);
        addGB(appplyBtn,  0, 10, 1, 1);

        resetBtn = new JButton("Reset Default");
        resetBtn.addActionListener(simulationEngine);
        resetBtn.addActionListener(this);
        addGB(resetBtn,  1, 10, 1, 1);

        JSeparator sep2 = new JSeparator();
        addGB(sep2,  0, 11, 2, 1);

        startBtn = new JButton("START");
        startBtn.addActionListener(simulationEngine);
        //startBtn.addActionListener(this);
        addGB(startBtn,  0, 12, 1, 1);

        stopBtn = new JButton("STOP");
        stopBtn.addActionListener(simulationEngine);
        //stopBtn.addActionListener(this);
        addGB(stopBtn,  1, 12, 1, 1);

        stepBtn = new JButton("Make Step");
        stepBtn.addActionListener(simulationEngine);
        //appplyBtn.addActionListener(this);
        addGB(stepBtn,  0, 13, 1, 1);

        saveBtn = new JButton("save");
        //saveBtn.addActionListener(simulationEngine);
        //saveBtn.addActionListener(this);
        addGB(saveBtn,  1, 13, 1, 1);

    }

    void addGB(Component component, int x, int y, int w, int h) {
        constraints.gridx = x;
        constraints.gridy = y;
        constraints.gridwidth = w;
        constraints.gridheight = h;

        constraints.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
        constraints.fill = (x == 0) ? GridBagConstraints.BOTH : GridBagConstraints.HORIZONTAL;

        constraints.weightx = (x == 0) ? 0.1 : 1.0;

        dataPanel.add(component, constraints);
    }

    public JPanel getDataPanel() {
        return dataPanel;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case "Apply changes":
                applyData();
                break;
            case "Reset Default":
                resetData();
                break;
        }
    }

    private void applyData() {
        dataManager.mapWidth = (int) widthSpinner.getValue();
        dataManager.mapHeight = (int) heightSpinner.getValue();
        dataManager.startAnimalNumber = (int) animalsNumberSpinner.getValue();
        dataManager.startEnergy = (double) startEnergySpinner.getValue();
        dataManager.moveEnergy = (double) moveEnergySpinner.getValue();
        dataManager.grassEnergy = (double) grassEnergySpinner.getValue();
        dataManager.jungleRatio = (double) jungleRatioSpinner.getValue();
        dataManager.twoMaps = twoMapCheckbox.isSelected();
    }

    private void resetData() {
        dataManager = new DataManager();
    }
}
