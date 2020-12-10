import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.jar.JarEntry;

import static javax.swing.BoxLayout.Y_AXIS;

public class MainWindow extends JFrame {

    private SimulationEngine simulationEngine;
    DataManager dataManager;
    DataPanel dataPanel;
    MapPanel mapa1;
    MapPanel mapa2;
    JPanel map1Panel;
    JPanel map2Panel;
    JPanel leftPanel;
    StatPanel statPanel1;
    StatPanel statPanel2;

    public MainWindow(SimulationEngine simulationEngine, DataManager dataManager, MapPanel mapa1, MapPanel mapa2, StatPanel statPanel1, StatPanel statPanel2) {
        this.simulationEngine = simulationEngine;
        this.dataManager = dataManager;
        this.mapa1 = mapa1;
        this.mapa2 = mapa2;
        this.statPanel1 = statPanel1;
        this.statPanel2 = statPanel2;

        mapa1.setMainWindow(this);
        mapa2.setMainWindow(this);

        dataPanel = new DataPanel(simulationEngine, dataManager);

        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));

        leftPanel.add(dataPanel);
        leftPanel.add(new Label("Map 1 Stats"));
        leftPanel.add(this.statPanel1);


        map1Panel = new JPanel();
        map1Panel.add(this.mapa1);

        map2Panel = new JPanel();
        map2Panel.add(mapa2);

        this.setTitle("Evolution Simulator");
        this.add(leftPanel, BorderLayout.WEST);
        this.add(map1Panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setPreferredSize(new Dimension(800, 800));
        this.setMinimumSize(new Dimension(800, 800));
        //this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.pack();
    }

    public void steerSecondMap(boolean is) {
        if (is) {
            leftPanel.add(new Label("Map 2 Stats"));
            leftPanel.add(this.statPanel2);
            this.add(map2Panel, BorderLayout.EAST);
        } else {
            leftPanel.remove(statPanel2);
            this.remove(map2Panel);
        }


    }
}
