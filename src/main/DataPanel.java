import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataPanel implements ActionListener {
    public int mapWidth;
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


    public DataPanel(SimulationEngine simulationEngine, DataManager dataManager){
        this.dataManager = dataManager;
        constraints = new GridBagConstraints();
        dataPanel = new JPanel();
        //dataPanel.setMaximumSize(new Dimension(300, 300));



        dataPanel.setLayout(new GridBagLayout());
        dataPanel.setPreferredSize(new Dimension(250, 500));


        widthSpinner = new JSpinner(new SpinnerNumberModel(5, 0, 50, 1));
        heightSpinner = new JSpinner(new SpinnerNumberModel(5, 0, 50, 1));
        animalsNumberSpinner = new JSpinner(new SpinnerNumberModel(5, 0, 50, 1));
        startEnergySpinner = new JSpinner(new SpinnerNumberModel(2.5, 0, 5.0, 0.1));
        moveEnergySpinner = new JSpinner(new SpinnerNumberModel(1.5, 0, 3.0, 0.1));
        grassEnergySpinner = new JSpinner(new SpinnerNumberModel(1.0, 0, 3.0, 0.1));
        jungleRatioSpinner = new JSpinner(new SpinnerNumberModel(0.50, 0, 1.0, 0.1));

        twoMapCheckbox = new JCheckBox();



        constraints.insets = new Insets(2,2,2,2);





        /*dataPanel.setLayout(new GridLayout(8, 1));

        widthPanel = new JPanel(new GridLayout(1, 2));
        widthPanel.add(new JLabel("Map Width"));
        mapWidthField = new JTextField();
        widthPanel.add(mapWidthField);
        dataPanel.add(widthPanel);

        JButton applyData = new JButton("apply Data");
        applyData.addActionListener(simulationEngine);


        dataPanel.add(applyData);*/

        int x, y;  // for clarity

        addGB(new JTextArea("Specify Simulation Data"), 0, 0, 2, 1);

        addGB(new JLabel("Map Width"),  0, 1, 1, 1);
        addGB(widthSpinner,  1, 1, 1, 1);

        addGB(new JLabel("Map Height"),  0, 2, 1, 1);
        addGB(heightSpinner,  1, 2, 1, 1);

        addGB(new JLabel("Starting animal number"),  0, 3, 1, 1);
        addGB(animalsNumberSpinner,  1, 3, 1, 1);
        //addGB(new JLabel("Starting animal number"),  0, 3, 1, 1);


        addGB(new JLabel("Starting Energy"),  0, 4, 1, 1);
        addGB(startEnergySpinner,  1, 4, 1, 1);
        //startEnergySpinner.setEditor(new JSpinner.NumberEditor(startEnergySpinner).);



        addGB(new JLabel("Move Energy"),  0, 5, 1, 1);
        addGB(moveEnergySpinner,  1, 5, 1, 1);

        addGB(new JLabel("Grass Energy"),  0, 6, 1, 1);
        addGB(grassEnergySpinner,  1, 6, 1, 1);

        addGB(new JLabel("Jungle Ratio"),  0, 7, 1, 1);
        addGB(jungleRatioSpinner,  1, 7, 1, 1);

        addGB(new JLabel("Two Maps"),  0, 8, 1, 1);
        addGB(twoMapCheckbox,  1, 8, 1, 1);



        JSeparator sep1 = new JSeparator();

        sep1.setBorder(new EmptyBorder(120, 120, 120, 120));

        addGB(sep1,  0, 9, 2, 1);




        JButton appplyBtn = new JButton("Apply Changes");
        //j.addActionListener(simulationEngine);
        appplyBtn.addActionListener(this);
        //appplyBtn.setPreferredSize(new Dimension(80, 40));
        addGB(appplyBtn,  0, 10, 1, 1);


        JButton resetBtn = new JButton("Reset Default");
        //j.addActionListener(simulationEngine);
        resetBtn.addActionListener(this);
        //resetBtn.setPreferredSize(new Dimension(80, 40));
        addGB(resetBtn,  1, 10, 1, 1);


        //sep.setMinimumSize(new Dimension());
        JSeparator sep2 = new JSeparator();
        addGB(sep2,  0, 11, 2, 1);

        JButton startBtn = new JButton("START");
        //j.addActionListener(simulationEngine);
        appplyBtn.addActionListener(this);
        addGB(startBtn,  0, 12, 1, 1);

        JButton stopBtn = new JButton("STOP");
        //j.addActionListener(simulationEngine);
        appplyBtn.addActionListener(this);
        addGB(stopBtn,  1, 12, 1, 1);

        JButton setBtn = new JButton("Make Step");
        //j.addActionListener(simulationEngine);
        appplyBtn.addActionListener(this);
        addGB(setBtn,  0, 13, 1, 1);

        JButton saveBtn = new JButton("save");
        //j.addActionListener(simulationEngine);
        appplyBtn.addActionListener(this);
        addGB(saveBtn,  1, 13, 1, 1);

        //addGB(new JButton("West"),   x = 0, y = 1);
        //addGB(new JButton("Center"), x = 1, y = 1);
        //addGB(new JButton("East"),   x = 2, y = 1);
        //addGB(new JButton("South"),  x = 1, y = 2);

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
        System.out.println(widthSpinner.getValue());
        dataManager.mapWidth = (int) widthSpinner.getValue();
    }
}
