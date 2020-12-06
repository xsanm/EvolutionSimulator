import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.jar.JarEntry;

public class MainWindow extends JFrame implements ComponentListener {

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
        //JFrame frame = new JFrame();
        //frame.setLayout(new GridLayout(1, 3));
        //mainPanel = new JPanel();
        //mainPanel.setLayout(new GridLayout(0, 3));
        //mainPanel.setLayout(new FlowLayout());
        //mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));


        DataManager dataManager = new DataManager();
        dataPanel = new DataPanel(simulationEngine, dataManager);


        //panel.setLayout(new GridLayout(0, 1));
        //panel.add(button);
        /*MapPanel mapPanel1 = new MapPanel();
        MapPanel mapPanel2 = new MapPanel();
        JPanel section1 = new JPanel();
        JPanel section2 = new JPanel();
        section2.setLayout( new FlowLayout());
        section1.add(mapPanel1.getMap());
        section2.add(mapPanel2.getMap(), BorderLayout.NORTH);
        //section2.add(new JButton(), BorderLayout.SOUTH);*/


        MapPanel mapa1 = new MapPanel(10, 10);
        //MapPanel mapa2 = new MapPanel(10, 10);
        //mapa1.setSize(new Dimension(25 * 130, 25 * 130));

        JPanel map1Pane = new JPanel();
        GridBagConstraints constraints = new GridBagConstraints();
        //map1Pane.setLayout(new GridBagLayout());
        //map1Pane.setPreferredSize(new Dimension(250, 500));
        constraints.gridx = 0;
        constraints.gridy = 0;
        //map1Pane.add(mapa1, constraints);
        map1Pane.add(mapa1);
        map1Pane.add(new Label("stat1"));

        JPanel map2Pane = new JPanel(new FlowLayout());
        //map2Pane.add(mapa2);

        //mainPanel.add(dataPanel.getDataPanel());
        //mainPanel.add(mapPanel.getMap());
        //mainPanel.add(applyData);

        this.setTitle("Evolution Simulator");
        this.add(dataPanel.getDataPanel(), BorderLayout.WEST);
        this.add(map1Pane, BorderLayout.CENTER);
        this.add(map2Pane, BorderLayout.EAST);
        //frame.add(section2, BorderLayout.EAST);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1000, 800));
        this.setVisible(true);
        this.pack();
        //frame.setResizable(false);

    }



    @Override
    public void componentResized(ComponentEvent e) {
        int W = 1;
        int H = 1;
        Rectangle b = e.getComponent().getBounds();
        e.getComponent().setBounds(b.x, b.y, b.width, b.width*H/W);

    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
