import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarEntry;

public class MainWindow {

    private final SimulationEngine simulationEngine;
    private JPanel mainPanel;
    DataPanel dataPanel;
    private JTextField mapWidth;
    private JTextField numberAnimals;
    private JTextArea evolutionSImulatorTextArea;
    private JTextField mapHeight;
    private JTextField moveEnergy;
    private JTextField startEnergy;
    private JTextField jungleRatio;
    private JTextField plantEnergy;
    private JCheckBox twoMapCheckbox;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton rightBtn;
    private JButton applyData;
    private JPanel map1Panel;

    public MainWindow(SimulationEngine simulationEngine) {
        this.simulationEngine = simulationEngine;
        JFrame frame = new JFrame();
        //frame.setLayout(new GridLayout(1, 3));
        mainPanel = new JPanel();
        //mainPanel.setLayout(new GridLayout(0, 3));
        mainPanel.setLayout(new FlowLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));


        DataManager dataManager = new DataManager();
        dataPanel = new DataPanel(simulationEngine, dataManager);


        //panel.setLayout(new GridLayout(0, 1));
        //panel.add(button);
        MapPanel mapPanel1 = new MapPanel();
        MapPanel mapPanel2 = new MapPanel();
        JPanel section1 = new JPanel();
        JPanel section2 = new JPanel();
        section2.setLayout( new FlowLayout());
        section1.add(mapPanel1.getMap());
        section2.add(mapPanel2.getMap(), BorderLayout.NORTH);
        //section2.add(new JButton(), BorderLayout.SOUTH);




        mainPanel.add(dataPanel.getDataPanel());
        //mainPanel.add(mapPanel.getMap());
        //mainPanel.add(applyData);

        frame.setTitle("Evolution Simulator");
        frame.add(dataPanel.getDataPanel(), BorderLayout.WEST);
        frame.add(section1, BorderLayout.CENTER);
        frame.add(section2, BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}
